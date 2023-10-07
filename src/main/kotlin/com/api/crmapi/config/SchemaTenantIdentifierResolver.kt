package com.api.crmapi.config

import org.hibernate.context.spi.CurrentTenantIdentifierResolver

class SchemaTenantIdentifierResolver: CurrentTenantIdentifierResolver {
  override fun resolveCurrentTenantIdentifier(): String {
    return TenantContext.getTenant()
  }

  override fun validateExistingCurrentSessions(): Boolean {
    return true
  }
}