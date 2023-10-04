package com.api.crmapi.entities

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "opportunities")
data class Opportunity (
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(name = "title")
  @NotNull(message = "Title is required")
  val title: String,

  @Column(name = "value")
  @NotNull(message = "Value is required")
  val value: Double,

  @Column(name = "status")
  @NotNull(message = "Status is required")
  val status: String,  // e.g., "Open", "Closed Won", "Closed Lost"

  @Column(name = "stage")
  @NotNull(message = "Stage is required")
  val stage: String,  // e.g., "Prospecting", "Negotiation", "Closed"

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer_id")
  val customer: Customer,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tenant_id")
  val tenant: Tenant
)