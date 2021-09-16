package com.armkrtchyan.tutorial.configurations.jwt

import org.springframework.security.access.AuthorizationServiceException

class InvalidJwtAuthenticationException(e: String?) : AuthorizationServiceException(e) {
    companion object {
        /**
         *
         */
        private const val serialVersionUID = -761503632186596342L
    }
}