package com.example.jwt.examplejwt.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer  /** Enables an authorization server */
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	/** defines the id of the client application that is authorized to authenticate, 
	 * the client application provides this in order to be allowed to send request to the server */
   @Value("${security.jwt.client-id}")
   private String clientId;

   /** is the client application’s password */
   @Value("${security.jwt.client-secret}")
   private String clientSecret;

   @Value("${security.jwt.grant-type}")
   private String grantType;

   /** read, write defines the level of access we are allowing to resources */
   @Value("${security.jwt.scope-read}")
   private String scopeRead;

   @Value("${security.jwt.scope-write}")
   private String scopeWrite = "write";

   /** The resource Id specified here must be specified on the resource server as well */
   @Value("${security.jwt.resource-ids}")
   private String resourceIds;

   @Autowired
   private TokenStore tokenStore;

   @Autowired
   private JwtAccessTokenConverter accessTokenConverter;

   /** Spring’s authentication manager takes care checking user credential validity */
   @Autowired
   private AuthenticationManager authenticationManager;

   @Override
   public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
      configurer
              .inMemory()
              .withClient(clientId)
              .secret(clientSecret)
              .authorizedGrantTypes(grantType)
              .scopes(scopeRead, scopeWrite)
              .resourceIds(resourceIds);
   }

   /** We define a token enhancer that enables chaining multiple types of claims containing different information */
   @Override
   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
      TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
      enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
      endpoints.tokenStore(tokenStore)
              .accessTokenConverter(accessTokenConverter)
              .tokenEnhancer(enhancerChain)
              .authenticationManager(authenticationManager);
   }

}
