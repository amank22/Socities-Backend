package com.sosity.plugins

import com.sosity.database.Db
import com.sosity.database.database
import io.ktor.server.application.*

fun Application.configureDatabases() {
    Db.init()
}
