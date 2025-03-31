package com.etsoft.interceptors

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class ExampleController {


    @PostMapping("/person")
    fun createPerson(@RequestBody personToBeCreated: Person): Person {
        println("paso por el controller")
        val createdPerson = personToBeCreated.copy(id = UUID.randomUUID().toString())
        return createdPerson
    }
}