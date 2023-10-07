package com.api.crmapi.config

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

class TenantRoutingDatasource: AbstractRoutingDataSource() {
  override fun determineCurrentLookupKey(): Any {
    return TenantContext.getTenant()
  }
}