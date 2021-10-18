package com.leacar21.technical.seat.function.reservation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.leacar21.technical.seat.function.reservation.controllers.ReservationController;

/**
 * Enables Spring security filter and configures some allowed urls that can be consumed without being authenticated
 * Also declares some Token util beans
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String[] SECURED_URLS = { //
            ReservationController.RESERVATIONS_RESOURCE, //
            ReservationController.RESERVATIONS_RESOURCE + "/**" //
    };

    @Value("${security.oauth2.resource.jwt.key-value}")
    private String jwtKey;

    @Override
    // Allow anonymous requests for swagger urls
    // OPTIONS methods allowed by default for all requests
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS)
            .anonymous()
            .antMatchers(SECURED_URLS)
            .authenticated()
            .anyRequest()
            .anonymous();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        final JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(this.jwtKey);
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

}
