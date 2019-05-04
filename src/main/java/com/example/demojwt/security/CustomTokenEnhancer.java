package com.example.demojwt.security;

import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String,Object> additionalInfo = new HashMap<>();
        additionalInfo.put("organization",oAuth2Authentication.getName() + randomAlphabetic(4));
        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(additionalInfo);
        return oAuth2AccessToken;
    }

    @Bean
    public OAuth2AccessToken readAccessToken(String tokenValue) throws EmptyResultDataAccessException,IllegalArgumentException {
        OAuth2AccessToken accessToken = null;

        return new DefaultOAuth2AccessToken(tokenValue);
    }
}
