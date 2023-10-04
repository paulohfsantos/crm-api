package com.api.crmapi.entities

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "user")
data class User (
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(name = "username")
  @NotNull(message = "Username is required")
  val username: String,

  @Column(name = "password")
  @NotNull(message = "Password is required")
  val password: String,

  @Column(name = "email")
  @NotNull(message = "Email is required")
  val email: String,

  @Column(name = "role")
  @NotNull(message = "Role is required")
  val role: String,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tenant_id")
  val tenant: Tenant
)