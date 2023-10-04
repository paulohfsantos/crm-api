package com.api.crmapi.entities

import javax.persistence.*

@Entity
@Table(name = "tenants")
data class Tenant (
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,
  val name: String,
  val domain: String,  // Unique identifier, e.g., subdomain or code

  @OneToMany(mappedBy = "tenant", cascade = [CascadeType.ALL])
  val users: List<User> = emptyList()
)