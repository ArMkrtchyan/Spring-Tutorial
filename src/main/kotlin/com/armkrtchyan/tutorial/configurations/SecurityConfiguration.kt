package com.armkrtchyan.tutorial.configurations

import com.armkrtchyan.tutorial.configurations.jwt.AuthProvider
import com.armkrtchyan.tutorial.configurations.jwt.JwtConfigurer
import com.armkrtchyan.tutorial.configurations.jwt.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


@Configuration
@EnableWebSecurity
class SecurityConfiguration @Autowired constructor(
        private val authProvider: AuthProvider,
        private val jwtTokenProvider: JwtTokenProvider
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
   //     auth?.authenticationProvider(authProvider)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/","/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(JwtConfigurer(jwtTokenProvider))
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager? {
        return super.authenticationManagerBean()
    }
}