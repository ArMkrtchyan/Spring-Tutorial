package com.armkrtchyan.tutorial.controller

import com.armkrtchyan.tutorial.configurations.jwt.AuthProvider
import com.armkrtchyan.tutorial.configurations.jwt.JwtTokenProvider
import com.armkrtchyan.tutorial.entity.SignInModel
import com.armkrtchyan.tutorial.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/users")
class UserController @Autowired constructor(
        private val authenticationManager: AuthProvider,
        private val jwtTokenProvider: JwtTokenProvider,
        private val userService: UserService
) {
    @PostMapping("/signIn")
    fun signIn(@RequestBody signInModel: SignInModel?): ResponseEntity<*> {
        return userService.signIn(signInModel, authenticationManager, jwtTokenProvider)
    }
}