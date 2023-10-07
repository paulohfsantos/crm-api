package com.api.crmapi.repositories

import com.api.crmapi.entities.TenantConfig
import org.springframework.data.jpa.repository.JpaRepository

interface TenantConfigRepository: JpaRepository<TenantConfig, String>