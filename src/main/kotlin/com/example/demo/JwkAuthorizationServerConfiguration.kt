package com.example.demo

import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.KeyUse
import com.nimbusds.jose.jwk.RSAKey
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory
import java.security.KeyPair
import java.security.interfaces.RSAPublicKey
import java.util.*


@Configuration
@EnableAuthorizationServer
 class JwkAuthorizationServerConfiguration {
 private val KEY_STORE_FILE = "bael-jwt.jks"
 private val KEY_STORE_PASSWORD = "bael-pass"
 private val KEY_ALIAS = "bael-oauth-jwt"
 private val JWK_KID = "bael-key-id"

 @Bean
 fun tokenStore(jwtAccessTokenConverter: JwtAccessTokenConverter): TokenStore {
  return JwtTokenStore(jwtAccessTokenConverter)
 }

 @Bean
 fun accessTokenConverter(): JwtAccessTokenConverter {
  val customHeaders = Collections.singletonMap("kid", JWK_KID)
  return JwtCustomHeadersAccessTokenConverter(customHeaders, keyPair())
 }

 @Bean
 fun keyPair(): KeyPair {
  val ksFile = ClassPathResource(KEY_STORE_FILE)
  val ksFactory = KeyStoreKeyFactory(ksFile, KEY_STORE_PASSWORD.toCharArray())
  return ksFactory.getKeyPair(KEY_ALIAS)
 }

 @Bean
 fun jwkSet(): JWKSet {
  val builder = RSAKey.Builder(keyPair().public as RSAPublicKey).keyUse(KeyUse.SIGNATURE)
          .algorithm(JWSAlgorithm.RS256)
          .keyID(JWK_KID)
  return JWKSet(builder.build())
 }
}