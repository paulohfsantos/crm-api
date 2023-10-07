package com.api.crmapi.service

import com.api.crmapi.entities.TenantConfig
import com.api.crmapi.repositories.TenantConfigRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
@Lazy
class TenantConfigurationService {

  @Autowired
  lateinit var repo: TenantConfigRepository

  fun findAll(): List<TenantConfig> = repo.findAll()
}
