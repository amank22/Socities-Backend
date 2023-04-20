package com.sosity.database

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.util.ObjectMappingConfiguration
import org.slf4j.LoggerFactory
import java.util.logging.Logger

val connectionString: String = System.getenv("MONGO_DB_URL") ?: "mongodb://localhost"
val client = KMongo.createClient(connectionString).coroutine //use coroutine extension
val database = client.getDatabase("societies")//normal java driver usage

object Db {

    fun init() {
        val loggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
        val rootLogger = loggerContext.getLogger("org.mongodb.driver")
        rootLogger.level = Level.OFF
        Logger.getGlobal().info(connectionString)
        database
        ObjectMappingConfiguration.serializeNull = false
    }

}

