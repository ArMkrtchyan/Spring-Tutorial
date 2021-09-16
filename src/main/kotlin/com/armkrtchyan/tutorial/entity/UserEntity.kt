package com.armkrtchyan.tutorial.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "users")
data class UserEntity(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        @Column()
        val email: String? = null,
        @Column(name = "first_name")
        val firstName: String? = null,
        @Column(name = "last_name")
        val lastName: String? = null,
        @Column(name = "password")
        val userPassword: String? = null,
): Serializable, UserDetails{

        @JsonIgnore
        override fun getAuthorities(): Collection<GrantedAuthority> {
                return arrayListOf()
        }

        override fun getPassword(): String {
                return "Password"
        }

        @JsonIgnore
        override fun getUsername(): String {
                return "arshak9292@gmail.com"
        }

        @JsonIgnore
        override fun isAccountNonExpired(): Boolean {
                return true
        }

        @JsonIgnore
        override fun isAccountNonLocked(): Boolean {
                return true
        }

        @JsonIgnore
        override fun isCredentialsNonExpired(): Boolean {
                return true
        }

        @JsonIgnore
        override fun isEnabled(): Boolean {
                return true
        }
}
