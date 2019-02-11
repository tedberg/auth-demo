package com.triview.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableOAuth2Client
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    // The @EnableResourceServer annotation enables a Spring Security filter
    // that authenticates requests via an incoming OAuth2 token.
    // So anything under /api will require an OAuth2 token to be present.

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("api");
    }

}
