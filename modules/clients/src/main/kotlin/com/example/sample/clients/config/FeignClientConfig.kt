package com.example.sample.clients.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["com.example.sample.clients"])
@Configuration
class FeignClientConfig