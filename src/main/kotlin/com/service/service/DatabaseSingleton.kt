package com.service.service

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.ktorm.database.Database

class DatabaseSingleton: KoinComponent {
    val database by inject<Database>()
}
