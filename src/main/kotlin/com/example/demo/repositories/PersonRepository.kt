package com.example.demo.repositories

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ldap.core.AttributesMapper
import org.springframework.ldap.core.LdapTemplate
import org.springframework.ldap.query.LdapQueryBuilder.query
import org.springframework.stereotype.Service

@Service
class PersonRepository (
        @Autowired
        val ldapTemplate: LdapTemplate
)
{
        fun getAllPersonNames(): List<String> {
                return  ldapTemplate.search(
                        query().where("objectclass").`is`("person"),
                        AttributesMapper<String> { attrs -> attrs.get("cn").toString() }
                )
        }
}