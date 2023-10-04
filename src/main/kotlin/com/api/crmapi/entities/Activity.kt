package com.api.crmapi.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "activity")
data class Activity (
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,

  @Column(name = "description")
  val description: String,

  @Column(name = "date")
  val date: LocalDateTime,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "opportunity_id")
  val opportunity: Opportunity,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "tenant_id")
  val tenant: Tenant
)