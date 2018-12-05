package com.example.oauth1.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.ExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class ProviderServerConfig extends AuthorizationServerConfigurerAdapter {

//    @Autowired
//    DataSource dataSource;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
//        security.checkTokenAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("chich").secret("chich")
               .scopes("app").authorizedGrantTypes("authorization_code")
               .redirectUris("http://localhost:9000/callback")
               .and().withClient("choe").secret("choe")
               .scopes("app").authorizedGrantTypes("implicit")
               .redirectUris("http://localhost:9000/callback")
               .accessTokenValiditySeconds(3600)
               .and().withClient("tac").secret("tac")
               .scopes("app").authorizedGrantTypes("password")
               .redirectUris("http://localhost:9000/callback")
               .and().withClient("ke").secret("ke")
               .authorizedGrantTypes("client_credentials", "password", "refresh_token")
               .accessTokenValiditySeconds(120)
               .refreshTokenValiditySeconds(300)
               .scopes("app");
//        clients.jdbc(dataSource);
    }


    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("tu hu con");
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
//        return new RedisTokenStore()
//        return new JdbcTokenStore(dataSource);
        return new JwtTokenStore(accessTokenConverter());
    }

//    @Bean
//    public ApprovalStore approvalStore() {
//        return new JdbcApprovalStore(dataSource);
//    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        endpoints.authenticationManager(authenticationManager);
        endpoints.tokenStore(tokenStore());
        endpoints.accessTokenConverter(accessTokenConverter());
//        endpoints.tokenStore(tokenStore());
//        endpoints.approvalStore(approvalStore());
    }
}
