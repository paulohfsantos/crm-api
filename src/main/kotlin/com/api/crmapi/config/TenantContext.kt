package com.api.crmapi.config

object TenantContext {
  private val tenantHolder = ThreadLocal<String>()

  fun setTenant(tenant: String) {
    tenantHolder.set(tenant)
  }

  fun getTenant(): String {
    return tenantHolder.get() ?: "public"
  }

  fun clear() {
    tenantHolder.remove()
  }

  fun getTenantId(): Long {
    return tenantHolder.get().toLong()
  }
}