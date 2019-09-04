package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer

@SpringBootApplication
//@EnableResourceServer
@EnableAuthorizationServer
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
