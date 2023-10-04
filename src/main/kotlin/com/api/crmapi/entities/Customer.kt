package com.api.crmapi.entities

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@Table(name = "customers")
data class Customer (
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,

  @Column(name = "name")
  @NotNull(message = "Name is required")
  val name: String,

  @OneToMany(mappedBy = "customer", cascade = [CascadeType.ALL])
  val contacts: List<Contact> = emptyList(),

  @OneToMany(mappedBy = "customer", cascade = [CascadeType.ALL])
  val opportunities: List<Opportunity> = emptyList(),

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tenant_id")
  val tenant: Tenant
)