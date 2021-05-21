package com.jpabuddy.kotlinentities

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectRepositoryTest(@Autowired val projectRepository: ProjectRepository) {

    @Test
    fun projectIsInitialized() {
        val project = projectRepository.findById(1)
        assertTrue(project.isPresent)
    }

    @Test
    fun projectHashSetIsConsistent() {
        val project = Project(name = "New project")
        val hashSet = hashSetOf(project)
        println(project.hashCode())

        projectRepository.save(project)
        println(project.hashCode())

        assertTrue(project in hashSet)
    }

    @Test
    fun toStringInsideTransaction() {
        val project = projectRepository.findById(1).get()
        println(project)
    }


    @Test
    fun projectIsSavedWithLateinit() {
        val project = projectRepository.save(Project().apply {
            name = "Andrew"
        })
        assertNotNull(project.id)
    }
}