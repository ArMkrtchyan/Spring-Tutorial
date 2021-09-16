package com.armkrtchyan.tutorial.repository

import com.armkrtchyan.tutorial.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
//    @Query("INSERT INTO users ", nativeQuery = true)
//    fun addAll(users: List<Users>)

}