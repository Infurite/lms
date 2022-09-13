package com.temchik.lms.service.user;

import com.temchik.lms.common.exception.BusinessEntityNotFoundException;
import com.temchik.lms.dto.user.AccountDTO;
import com.temchik.lms.model.user.Account;
import com.temchik.lms.repository.user.AccountRepository;
import com.temchik.lms.service.mapper.user.AccountDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;
    private final AccountDTOMapper mapper;

    /**
     * Get an account information by User UUID
     *
     * @param userId - user UUID
     * @return user accountDTO
     */
    public AccountDTO get(UUID userId) {
        return mapper.toDTO(findByUserId(userId));
    }

    /**
     * Create an user account
     *
     * @param userId - user UUID
     * @param dio    - @{@link AccountDTO}
     * @return the created user accountDTO
     */
    @Transactional
    public AccountDTO create(UUID userId, AccountDTO dio) {
        Account account = mapper.toEntity(dio);
        account.setUserId(userId);
        account.setCreatedAt(new Date(System.currentTimeMillis()));
        return mapper.toDTO(repository.save(account));
    }

    /**
     * Find account by userId
     *
     * @param userId - user UUID
     * @return User account entity
     */
    public Account findByUserId(UUID userId) {
        return repository.findByUserId(userId)
                .orElseThrow(() -> new BusinessEntityNotFoundException(Account.class, "id"));
    }
}
