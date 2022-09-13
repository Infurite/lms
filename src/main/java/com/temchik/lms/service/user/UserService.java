package com.temchik.lms.service.user;

import com.temchik.lms.common.constants.account.UserTokenType;
import com.temchik.lms.common.exception.BusinessEntityNotFoundException;
import com.temchik.lms.common.exception.BusinessException;
import com.temchik.lms.config.tenant.TenantContext;
import com.temchik.lms.dto.user.UserDTO;
import com.temchik.lms.event.user.event.UserApprovedEvent;
import com.temchik.lms.event.user.event.UserCreatedEvent;
import com.temchik.lms.model.user.User;
import com.temchik.lms.model.user.UserToken;
import com.temchik.lms.repository.user.UserRepository;
import com.temchik.lms.service.mapper.user.UserDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserTokenService tokenService;
    private final ApplicationEventPublisher eventPublisher;

    private final UserRepository repository;
    private final UserDTOMapper mapper;

    /**
     * Auth for Spring Security
     *
     * @see org.springframework.security.core.userdetails.UserDetailsService
     */
    @Transactional(readOnly = true)
    User loadByEmail(String email) {
        return repository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new BusinessEntityNotFoundException(User.class, "email"));
    }

    /**
     * Get an user information
     *
     * @param id - user UUID
     * @return UserDTO
     */
    public UserDTO get(UUID id) {
        return mapper.toDTO(findById(id));
    }

    /**
     * Create a new user entity
     *
     * @param dio - @{@link UserDTO}
     * @return UserDTO
     */
    @Transactional
    public UserDTO create(UserDTO dio) {
        checkExistByEmail(dio.getEmail());

        User user = repository.save(mapper.toEntity(dio));
        UserToken token = tokenService.generateToken(user, UserTokenType.APPROVE_ACCOUNT);
        UserDTO doo = mapper.toDTO(user);

        eventPublisher.publishEvent(new UserCreatedEvent(this, token));
        return doo;
    }

    /**
     * User email confirmation
     *
     * @param token generated user token
     * @return approved @{@link UserDTO}
     */
    @Transactional
    public UserDTO confirmUser(String token) {
        UserToken userToken = tokenService.validateUserToken(UserTokenType.APPROVE_ACCOUNT, token);

        User user = userToken.getUser();
        user.setActivated(true);
        tokenService.deactivateToken(token);
        UserDTO doo = mapper.toDTO(repository.save(user));

        eventPublisher.publishEvent(new UserApprovedEvent(this, user));
        return doo;
    }

    @Transactional(readOnly = true)
    public User findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new BusinessEntityNotFoundException(User.class, "id"));
    }

    private void checkExistByEmail(String email) {
        if (repository.existsByEmailIgnoreCase(email)) {
            throw new BusinessException("User with email = " + email + " exist");
        }
    }

    @Transactional(readOnly = true)
    public User findByIdAndCurrentTenant(UUID id) {
        return repository.findByIdAndTenantId(id, TenantContext.getTenantId())
                .orElseThrow(() -> new BusinessException("Request incorrect. User for current tenant not found"));
    }
}
