package com.service.service.model

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object UserTable: Table<User>("user") {
    val id = int("id").primaryKey()
    val name = varchar("name")
    val visitCount = int("visitCount")
}

interface User : Entity<User> {
    val id: Int
    var name: String
    var visitCount: Int
}
