package com.api.crmapi.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class TenantInterceptor: HandlerInterceptor {
//  companion object {
//    const val TENANT_HEADER = "X-TenantID"
//  }
//
//  override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
//    val tenant = request.getHeader(TENANT_HEADER)
//    if (tenant != null) {
//      TenantContext.setTenant(tenant)
//    } else {
//      throw IllegalArgumentException("No tenant header provided")
//    }
//    return true
//  }
//
//  override fun afterCompletion(
//    request: HttpServletRequest,
//    response: HttpServletResponse,
//    handler: Any, ex: Exception?
//  ) {
//    TenantContext.clear()
//  }
  override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
    val tenantId = request.getHeader("X-TenantID")
    TenantContext.setTenant(tenantId)
    return true
  }

  override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
    TenantContext.clear()
  }
}