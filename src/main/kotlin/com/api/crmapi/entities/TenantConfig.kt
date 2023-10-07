package com.api.crmapi.entities

import javax.persistence.*

@Entity
@Table(name = "tenant_configuration")
data class TenantConfig (
  @Id
  val tenantId: String,
  val jdbcUrl: String,
  val dbUsername: String,
  val dbPassword: String
)