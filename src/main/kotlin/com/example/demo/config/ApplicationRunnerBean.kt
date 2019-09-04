package com.example.demo.config

import com.example.demo.repositories.PersonRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ApplicationRunnerBean(
        @Autowired
        val personRepository: PersonRepository
): CommandLineRunner {
    val logger: Logger = LoggerFactory.getLogger(ApplicationRunnerBean::class.java)

    override fun run(vararg args: String?) {
        logger.info("Spring LDAP + Spring Boot Configuration Example")

        val names = personRepository.getAllPersonNames()
        logger.info("names: $names")
    }
}