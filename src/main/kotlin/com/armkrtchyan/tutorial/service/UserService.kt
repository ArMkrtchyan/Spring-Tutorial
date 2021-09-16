package com.armkrtchyan.tutorial.service

import com.armkrtchyan.tutorial.configurations.jwt.AuthProvider
import com.armkrtchyan.tutorial.configurations.jwt.JwtTokenProvider
import com.armkrtchyan.tutorial.entity.SignInModel
import com.armkrtchyan.tutorial.entity.TokenModel
import com.armkrtchyan.tutorial.entity.UserEntity
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService : UserDetailsService {

    fun signIn(signInModel: SignInModel?, authenticationManager: AuthProvider, jwtTokenProvider: JwtTokenProvider): ResponseEntity<*> {
        try {
             authenticationManager.authenticate(UsernamePasswordAuthenticationToken(signInModel?.username, signInModel?.password))
        } catch (e: BadCredentialsException) {
            return ResponseEntity.ok(TokenModel(e.message?:""))
        }

        val token = jwtTokenProvider.createToken(signInModel?.username)
        return ResponseEntity.ok(TokenModel(token))
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        return UserEntity()
    }
}