package com.example.authserver.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
public class AuthorizationServerConfig implements AuthorizationServerConfigurer {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	TokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clientdetails) throws Exception {

		clientdetails.jdbc(dataSource).passwordEncoder(passwordEncoder);
		
//		clientdetails.inMemory().withClient("foo").secret(passwordEncoder.encode("bar")).scopes("READ","WRITE").authorizedGrantTypes("authorization_code","refresh_token","password","client_credentials");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endPoints) throws Exception {
		
		endPoints.tokenStore(tokenStore());
		endPoints.authenticationManager(authenticationManager);
	}
	
	@Bean
	public TokenStore tokenStore() {
		
		return  new JdbcTokenStore(dataSource);
	}

}
