package com.jpabuddy.kotlinentities

import org.hibernate.collection.internal.PersistentBag
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeamRepositoryTest(@Autowired val teamRepository: TeamRepository) {


    @Test
    fun databaseIsInitialized() {
        val team = teamRepository.findById(1)
        assertTrue(team.isPresent)
    }

    @Test
    fun teamMembersArePresent() {
        val team = teamRepository.findById(1).get()
        assertFalse(team.teamMember!!.isEmpty())
    }

    @Test
    fun teamMembersAreInitializedWithAProxy() {
        val team = teamRepository.findById(1).get()
        assertTrue(team.teamMember is PersistentBag)
    }

    @Test
    fun projectIsAProxy() {
        val team = teamRepository.findById(1).get()
        assertNotNull(team.project)
        assertTrue(team.project!!.javaClass.name.contains("HibernateProxy"))
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    fun toStringOutsideTransaction() {
        val team = teamRepository.findById(1).get()
        println(team)
    }

}