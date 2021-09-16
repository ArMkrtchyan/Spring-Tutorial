package com.armkrtchyan.tutorial

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.Scope
import org.springframework.core.ParameterizedTypeReference
import org.springframework.data.jpa.repository.Query
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import javax.annotation.PreDestroy

@SpringBootApplication
class TutorialApplication

fun main(args: Array<String>) {
    val context = runApplication<TutorialApplication>(*args)
}