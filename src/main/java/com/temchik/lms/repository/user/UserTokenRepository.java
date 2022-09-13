package com.temchik.lms.repository.user;

import com.temchik.lms.common.constants.account.UserTokenType;
import com.temchik.lms.model.user.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, String> {

    @Query("select ut FROM UserToken ut WHERE " +
            "ut.isActive = true and " +
            "ut.token = :token and " +
            "ut.type = :type and " +
            "ut.expiredAt > CURRENT_TIMESTAMP")
    UserToken getValidUserToken(@Param("type") UserTokenType type, @Param("token") String token);

    @Query("SELECT CASE WHEN COUNT(ut) > 0 THEN true ELSE false END FROM UserToken ut WHERE " +
            "ut.isActive = true and " +
            "ut.user.id = :userId and " +
            "ut.type = :type and " +
            "ut.expiredAt > CURRENT_TIMESTAMP")
    boolean isTokenExists(@Param("userId") UUID userId, @Param("type") UserTokenType type);

    @Modifying
    @Query("UPDATE UserToken ut SET ut.isActive = false WHERE ut.user.id = :userId and ut.type = :type")
    void deactivateToken(@Param("userId") UUID userId, @Param("type") UserTokenType type);

    @Modifying
    @Query("UPDATE UserToken ut SET ut.isActive = false WHERE ut.token = :token")
    void deactivateToken(@Param("token") String token);
}
