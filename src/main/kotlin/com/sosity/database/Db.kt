package com.sosity.database

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo
import org.litote.kmongo.util.ObjectMappingConfiguration
import org.slf4j.LoggerFactory

val connectionString: String = System.getProperty("MONGO_DB_URL", "mongodb://localhost")
val client = KMongo.createClient(connectionString).coroutine //use coroutine extension
val database = client.getDatabase("societies")//normal java driver usage

object Db {

    fun init() {
        val loggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
        val rootLogger = loggerContext.getLogger("org.mongodb.driver")
        rootLogger.level = Level.OFF
        database
        ObjectMappingConfiguration.serializeNull = false
    }

}

