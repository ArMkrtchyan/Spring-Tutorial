package com.armkrtchyan.tutorial.configurations.jwt

import com.armkrtchyan.tutorial.entity.UserEntity
import com.armkrtchyan.tutorial.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class AuthProvider @Autowired constructor(
        private val bCryptPasswordEncoder: BCryptPasswordEncoder,
        private val userService: UserService
) : AuthenticationProvider {
    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val phone = authentication.name
        val password = authentication.credentials as String
        val userModel = userService.loadUserByUsername(phone) as UserEntity
        return if (userModel != null && userModel.username == phone) {
            if (!bCryptPasswordEncoder.matches(password, bCryptPasswordEncoder.encode(userModel.password))) {
                throw BadCredentialsException("Wrong password")
            }
            val authorities = userModel.authorities
            UsernamePasswordAuthenticationToken(userModel, password, authorities)
        } else throw BadCredentialsException("User" +
                " not found")
    }

    override fun supports(aClass: Class<*>?): Boolean {
        return false
    }

}