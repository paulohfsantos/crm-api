package com.api.crmapi.config

import com.zaxxer.hikari.HikariDataSource
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import org.springframework.beans.factory.FactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfig {
  @Bean
  fun dataSource(): DataSource {
    val dataSource = HikariDataSource()
    val DB_HOST = System.getenv("DB_HOST")
    val DB_PORT = System.getenv("DB_PORT")
    val DB = System.getenv("DB")
    val DB_USER = System.getenv("DB_USER")
    val DB_PASSWORD = System.getenv("DB_PASS")
    val DB_SCHEMA = System.getenv("DB_SCHEMA")

    dataSource.jdbcUrl = "jdbc:postgresql://$DB_HOST:$DB_PORT/$DB"
    dataSource.username = DB_USER
    dataSource.password = DB_PASSWORD
    dataSource.schema = DB_SCHEMA
    SchemaBasedMultiTenantConnectionProvider.setDataSource(dataSource)
    return dataSource
  }

  @Bean
  fun multiTenantConnectionProvider(dataSource: DataSource): FactoryBean<MultiTenantConnectionProvider> {
    return object : FactoryBean<MultiTenantConnectionProvider> {
      override fun getObject(): MultiTenantConnectionProvider {
        return SchemaBasedMultiTenantConnectionProvider(dataSource)
      }

      override fun getObjectType(): Class<*> {
        return SchemaBasedMultiTenantConnectionProvider::class.java
      }
    }
  }

  @Bean
  fun jpaProperties(multiTenantConnectionProvider: MultiTenantConnectionProvider): Map<String, Any> {
    val props = HashMap<String, Any>()
    props["hibernate.multiTenancy"] = "SCHEMA"
    props["hibernate.tenant_identifier_resolver"] = SchemaTenantIdentifierResolver::class.java.name
    props["hibernate.multi_tenant_connection_provider"] = multiTenantConnectionProvider
    return props
  }
}
