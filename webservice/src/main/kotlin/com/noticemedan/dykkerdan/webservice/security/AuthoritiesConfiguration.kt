package com.noticemedan.dykkerdan.webservice.security

import dk.acto.auth.client.providers.AuthoritiesProvider
import dk.acto.auth.client.providers.PublicKeyProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.client.RestTemplate
import java.util.*

@Configuration
class AuthoritiesConfiguration {
    @Bean
    fun authoritiesProvider(): AuthoritiesProvider {
        return AuthoritiesProvider { claims: Map<String, Any> ->
            Optional.ofNullable(claims["iss"])
                    .map { x -> x as String}
                    .map { x -> listOf(SimpleGrantedAuthority(x) as GrantedAuthority) }
                    .orElse(listOf())
        }
    }
    @Bean
    fun publicKeyProvider(
            @Value("\${FAFNIR_URL}") fafnirUrl: String,
            @Value("\${FAFNIR_PORT}") fafnirPort: String): PublicKeyProvider {
        return PublicKeyProvider { RestTemplate().getForObject(String.format("http://%s:%s/public-key", fafnirUrl, fafnirPort), String::class.java) }
    }
}