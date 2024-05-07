package com.pang.skeleton.jpa.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = ["com.pang.skeleton.jpa"])
@EnableJpaRepositories(basePackages = ["com.pang.skeleton.jpa"])
internal class JpaConfig
