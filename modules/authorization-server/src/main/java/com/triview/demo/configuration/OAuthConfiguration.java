package com.triview.demo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

    // Notes about compatibility with Spring Security 5.1
    // https://docs.spring.io/spring-security-oauth2-boot/docs/current-SNAPSHOT/reference/htmlsingle/#oauth2-boot-authorization-server-spring-security-oauth2-resource-server

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userService;

    private final String clientId;
    private final String clientSecret;
    private final int accessTokenValiditySeconds;
    private final int refreshTokenValiditySeconds;
    private final String[] authorizedGrantTypes;

    public OAuthConfiguration(AuthenticationManager authenticationManager,
                              PasswordEncoder passwordEncoder,
                              UserDetailsService userService,
                              @Value("${security.oauth2.client.client-id}") String clientId,
                              @Value("${security.oauth2.client.client-secret}") String clientSecret,
                              @Value("${security.oauth2.client.access-token-validity-seconds}") int accessTokenValiditySeconds,
                              @Value("${security.oauth2.client.refresh-token-validity-seconds}") int refreshTokenValiditySeconds,
                              @Value("${security.oauth2.client.authorized-grant-types}") String[] authorizedGrantTypes) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
            .withClient(clientId)
            .secret(passwordEncoder.encode(clientSecret))
            .accessTokenValiditySeconds(accessTokenValiditySeconds)
            .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
            .authorizedGrantTypes(authorizedGrantTypes)
            .scopes("read", "write")
            .resourceIds("api");
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            .accessTokenConverter(accessTokenConverter())
            .userDetailsService(userService)
            .authenticationManager(authenticationManager);
    }

    @Bean
    JwtAccessTokenConverter accessTokenConverter() {
        return new JwtAccessTokenConverter();
    }

}

