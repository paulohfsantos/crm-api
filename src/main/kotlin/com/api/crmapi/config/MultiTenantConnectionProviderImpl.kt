package com.api.crmapi.config

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.sql.Connection

@Component
class MultiTenantConnectionProviderImpl: MultiTenantConnectionProvider {

  private val actualProvider = SchemaBasedMultiTenantConnectionProvider(
    SchemaBasedMultiTenantConnectionProvider.getDataSource()
  )

  override fun getAnyConnection(): Connection {
    return actualProvider.getAnyConnection()
  }

  override fun releaseAnyConnection(connection: Connection) {
    actualProvider.releaseAnyConnection(connection)
  }

  override fun getConnection(tenantIdentifier: String): Connection {
    return actualProvider.getConnection(tenantIdentifier)
  }

  override fun releaseConnection(tenantIdentifier: String, connection: Connection) {
    actualProvider.releaseConnection(tenantIdentifier, connection)
  }

  override fun supportsAggressiveRelease(): Boolean {
    return actualProvider.supportsAggressiveRelease()
  }

  override fun isUnwrappableAs(unwrapType: Class<*>): Boolean {
    return actualProvider.isUnwrappableAs(unwrapType)
  }

  override fun <T : Any?> unwrap(unwrapType: Class<T>): T? {
    return actualProvider.unwrap(unwrapType)
  }
}