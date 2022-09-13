package com.temchik.lms.service.user;

import com.temchik.lms.common.constants.account.UserTokenType;
import com.temchik.lms.common.exception.BusinessException;
import com.temchik.lms.common.util.StringUtils;
import com.temchik.lms.model.user.User;
import com.temchik.lms.model.user.UserToken;
import com.temchik.lms.repository.user.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserTokenService {

    private final UserTokenRepository repository;

    public UserToken validateUserToken(UserTokenType type, String token) {
        UserToken userToken = repository.getValidUserToken(type, token);
        if (userToken == null) {
            throw new BusinessException("The token is not valid!");
        } else {
            return userToken;
        }
    }

    @Transactional
    public UserToken generateToken(User user, UserTokenType type) {
        if (repository.isTokenExists(user.getId(), type)) {
            repository.deactivateToken(user.getId(), type);
        }

        UserToken token = new UserToken();
        token.setToken(StringUtils.generateRandomString(50));
        token.setUser(user);
        token.setType(type);
        token.setActive(true);
        token.setExpiredAt(new Date(System.currentTimeMillis() + 3_600_000));

        return repository.save(token);
    }

    public void deactivateToken(String token) {
        repository.deactivateToken(token);
    }
}
