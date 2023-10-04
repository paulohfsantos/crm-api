package com.api.crmapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CrmApiApplication

fun main(args: Array<String>) {
  runApplication<CrmApiApplication>(*args)
}
