package com.pang.skeleton.clients.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["com.pang.skeleton.clients"])
@Configuration
class FeignClientConfig