package com.temchik.lms.config.security;

import com.temchik.lms.common.exception.BusinessException;
import com.temchik.lms.config.tenant.TenantContext;
import com.temchik.lms.model.user.Role;
import com.temchik.lms.model.user.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (!authentication.isClientOnly()) {
            User user = (User) authentication.getPrincipal();
            DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;

            final Map<String, Object> additionalInfo = new HashMap<>();
            additionalInfo.put("tenantId", user.getTenantId());
            additionalInfo.put("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
            token.setAdditionalInformation(additionalInfo);
        }
        return super.enhance(accessToken, authentication);
    }

    @Override
    protected Map<String, Object> decode(String token) {
        try {
            Map<String, Object> claims = super.decode(token);
            TenantContext.setTenantId(UUID.fromString((String) claims.get("tenantId")));
            return claims;
        } catch (NullPointerException e) {
            throw new BusinessException("Token incorrect");
        }
    }
}
