package com.example.sample.domain.config

import com.zaxxer.hikari.HikariDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.support.TransactionSynchronizationManager
import javax.sql.DataSource


@Configuration
@EnableConfigurationProperties(
    value = [
        JpaProperties::class,
    ]
)
internal class DataSourceConfig(
    private val jpaProperties: JpaProperties,
) {
    @Bean
    @ConfigurationProperties(prefix = "spring.jpa.datasource.read")
    fun readDataSource(): DataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.jpa.datasource.write")
    fun writeDataSource(): DataSource {
        return DataSourceBuilder.create().type(HikariDataSource::class.java).build()
    }

    @Bean
    @DependsOn("writeDataSource", "readDataSource")
    fun routeDataSource(): DataSource {
        val dataSourceRouter = DataSourceRouter()
        val writeDataSource: DataSource = writeDataSource()
        val readDataSource: DataSource = readDataSource()
        val dataSourceMap = HashMap<Any, Any>()
        dataSourceMap["write"] = writeDataSource
        dataSourceMap["read"] = readDataSource
        dataSourceRouter.setTargetDataSources(dataSourceMap)
        dataSourceRouter.setDefaultTargetDataSource(writeDataSource)
        return dataSourceRouter
    }

    @Bean
    @Primary
    @DependsOn("routeDataSource")
    fun dataSource(): DataSource {
        return LazyConnectionDataSourceProxy(routeDataSource())
    }

    @Bean
    fun entityManagerFactory(
        dataSource: DataSource,
    ): LocalContainerEntityManagerFactoryBean {
        val localContainerEntityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        localContainerEntityManagerFactoryBean.persistenceUnitName = "example"
        localContainerEntityManagerFactoryBean.dataSource = dataSource
        localContainerEntityManagerFactoryBean.jpaVendorAdapter = HibernateJpaVendorAdapter()
        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties)
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.example.sample.domain")
        return localContainerEntityManagerFactoryBean
    }

    @Bean
    fun transactionManager(
        entityManagerFactory: EntityManagerFactory,
    ): JpaTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory
        transactionManager.defaultTimeout = 5
        return transactionManager
    }
}


internal class DataSourceRouter : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any {
        val readOnly: Boolean = TransactionSynchronizationManager.isCurrentTransactionReadOnly()
        return if (readOnly) "read" else "write"
    }
}