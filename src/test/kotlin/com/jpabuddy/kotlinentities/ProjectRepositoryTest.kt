package com.jpabuddy.kotlinentities

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectRepositoryTest(
    @Autowired val projectRepository: ProjectRepository,
    @Autowired val clientRepository: ClientRepository,
//    @Autowired val valuableClientRepository: ValuableClientRepository,
) {

    @Test
    fun projectIsInitialized() {
        val project = projectRepository.findById(1)
        assertTrue(project.isPresent)
    }

    @Test
    internal fun clientIsAProxy() {
        val project = projectRepository.findById(1).get()
        val clientClassName = project.client?.javaClass?.name
        println(clientClassName)
        assertTrue(clientClassName?.contains("HibernateProxy") ?: false)
    }

    @Test
    internal fun equalsTest() {
        val project = projectRepository.findById(1).get()
        assertTrue(project == project.copy())
    }

/*    @Test
    internal fun checkNegativeIdInsert() {
        val project = Project().apply {
            name = "New new project"
        }
        val saved = projectRepository.save(project)
        assertTrue(project.isNew() && !saved.isNew())
    }*/

    @Test
    internal fun lateInitWorks() {
        val client = clientRepository.findById(1).get()
        println(client.name)
    }

//    @Test
//    internal fun dataclassChild() {
//        val clients = clientRepository.findAll()
//        val client = clientRepository.findById(1L).get()
//        val valuableClient = valuableClientRepository.findById(1L).get()
//
//        assertTrue(valuableClient != client)
//    }

}