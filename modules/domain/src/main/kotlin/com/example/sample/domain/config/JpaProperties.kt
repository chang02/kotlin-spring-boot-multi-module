package com.example.sample.domain.config

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.*

@ConfigurationProperties("spring.jpa.properties")
class JpaProperties : Properties() {
    lateinit var hibernate: Properties
}