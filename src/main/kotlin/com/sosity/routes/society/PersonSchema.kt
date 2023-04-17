package com.sosity.routes.society

import com.mongodb.client.model.InsertOneOptions
import com.mongodb.client.model.UpdateOptions
import com.sosity.database.database
import com.sosity.models.BaseDbModel
import com.sosity.routes.society.tables.Person
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.litote.kmongo.Id
import org.litote.kmongo.id.IdGenerator

object PersonSchema {

    private val personCollection = database.getCollection<Person>()

    suspend fun index() = withContext(Dispatchers.Default) {
        val stringBuilder = StringBuilder("Following indexes are created = [")
        stringBuilder.append(personCollection.ensureIndex(Person::name))
        stringBuilder.append(",")
        stringBuilder.append(personCollection.ensureUniqueIndex(Person::mobile))
        stringBuilder.append(",")
        stringBuilder.append(personCollection.ensureUniqueIndex(Person::houseNumber))
        stringBuilder.append(",")
        stringBuilder.append(personCollection.ensureIndex(Person::email))
        stringBuilder.append("]")
        stringBuilder.toString()
    }

    suspend fun get(batchSize: Int = 20, page: Int = 0, full: Boolean = false) = withContext(Dispatchers.Default) {
        var find = personCollection.find()
        if (!full) {
            find = find.projection(
                BaseDbModel<*>::id,
                BaseDbModel<*>::updateDateTime,
                BaseDbModel<*>::createDateTime,
                Person::name,
                Person::email,
                Person::mobile,
                Person::houseNumber,
                Person::photo,
                Person::residentialAddress,
                Person::activeCorporation
            )
        }
        find.skip(page * batchSize).limit(batchSize).partial(true).toList()
    }

    suspend fun get(id: String) = withContext(Dispatchers.Default) {
        val dbId = try {
            IdGenerator.defaultGenerator.create(id)
        } catch (e : Exception) {
            null
        } ?: return@withContext null
        personCollection.findOneById(dbId)
    }

    suspend fun insert(person: Person) = withContext(Dispatchers.Default) {
        personCollection.insertOne(person, InsertOneOptions().comment("Adding person -> ${person.name}"))
    }

    suspend fun update(id: Id<Person>, person: Person) = withContext(Dispatchers.Default) {
        personCollection.updateOneById(id, person, UpdateOptions().comment("Adding person -> ${person.name}"))
    }
}