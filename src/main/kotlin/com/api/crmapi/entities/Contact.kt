package com.api.crmapi.entities

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "contacts")
data class Contact (
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(name = "name")
  @NotNull(message = "Name is required")
  val name: String,

  @Column(name = "email")
  @NotNull(message = "Email is required")
  val email: String,

  @Column(name = "phone")
  @NotNull(message = "Phone is required")
  val phone: String? = null,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  val customer: Customer,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tenant_id")
  val tenant: Tenant
)