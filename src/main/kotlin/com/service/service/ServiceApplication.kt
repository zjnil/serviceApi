package com.service.service

import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.ktorm.database.Database
import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServiceApplication

fun main(args: Array<String>) {
	runApplication<ServiceApplication>(*args) {
		val database = Database.connect(
			"jdbc:mysql://34.141.3.30:3306/fun7",
			user = "root",
			password = "h3E7JsNeaJg45blg"
		)

		val mainModules = module {
			single { database }
		}

		startKoin() {
			modules(mainModules)
		}
	}
}
