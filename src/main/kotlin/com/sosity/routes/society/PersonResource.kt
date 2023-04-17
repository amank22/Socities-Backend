package com.sosity.routes.society

import com.sosity.routes.BaseApiResource
import io.ktor.resources.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("person")
class PersonResource(val parent : BaseApiResource = BaseApiResource(),
                     val batchSize : Int = 20,
                     val page : Int = 0,
                     val full : Boolean = false,
    ) {

    @Serializable
    @Resource("detail/{id}")
    class Id(val parent: PersonResource = PersonResource(), val id: String)
    @Serializable
    @Resource("generate")
    class Generate(val parent: PersonResource = PersonResource(), val id: String)
}