package com.example.demo.controller

import com.nimbusds.jose.jwk.JWKSet
import net.minidev.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class KeyMasterController(
        @Autowired
        private val jwkSet: JWKSet? = null
) {
    @GetMapping("/.well-known/jwks.json")
    fun jwt(): JSONObject? {
        return this.jwkSet?.toJSONObject();
    }
}