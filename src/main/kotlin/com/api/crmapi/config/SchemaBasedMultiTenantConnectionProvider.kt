package com.api.crmapi.config

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.sql.Connection
import javax.sql.DataSource

@Component
class SchemaBasedMultiTenantConnectionProvider(private val dataSource: DataSource): MultiTenantConnectionProvider {

  companion object {
    private var staticDataSource: DataSource? = null

    fun setDataSource(dataSource: DataSource) {
      staticDataSource = dataSource
    }

    fun getDataSource(): DataSource {
      return staticDataSource ?: throw IllegalStateException("DataSource not initialized")
    }
  }
  override fun getAnyConnection(): Connection {
    return dataSource.connection
  }

  override fun releaseAnyConnection(connection: Connection) {
    connection.close()
  }

  override fun supportsAggressiveRelease(): Boolean {
    return true
  }

  override fun getConnection(tenantIdentifier: String): Connection {
    val connection = anyConnection
    connection.schema = tenantIdentifier
    return connection
  }

  override fun releaseConnection(tenantIdentifier: String, connection: Connection) {
    connection.schema = null
    releaseAnyConnection(connection)
  }

  override fun isUnwrappableAs(unwrapType: Class<*>): Boolean {
    return false
  }

  override fun <T : Any?> unwrap(unwrapType: Class<T>): T? {
    return null
  }
}