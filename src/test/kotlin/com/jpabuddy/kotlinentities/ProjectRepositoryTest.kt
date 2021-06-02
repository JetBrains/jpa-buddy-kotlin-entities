package com.jpabuddy.kotlinentities

import org.hibernate.proxy.HibernateProxy
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.platform.commons.logging.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.lang.Exception
import java.util.*

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProjectRepositoryTest(
    @Autowired val projectRepository: ProjectRepository,
    @Autowired val clientRepository: ClientRepository,
    @Autowired val contactRepository: ContactRepository
) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(ProjectRepositoryTest::class.java)
    }

    @Test
    internal fun projectIsInitialized() {
        val project = projectRepository.findById(1)
        assertTrue(project.isPresent)
    }

    @Test
    internal fun lazyLoadEnabled() {
        val project = projectRepository.findById(1).get()
        val client = project.client
        LOGGER.info { "Class used for the client reference: ${client::class.java}" }
        assertTrue(HibernateProxy::class.java.isAssignableFrom(client::class.java))
    }

    @Test
    internal fun equalsIssue() {
        val contact = contactRepository.findById("john@verybigclient.com").get()
        assertTrue(contact == contact.copy())
    }

    @Test
    internal fun hashCodeIsConsistent() {
        val awesomeClient = clientRepository.findById(1).get()
        val awesomeProject = Project().apply {
            name = "Awesome project"
            client = awesomeClient
        }
        val hashSet = hashSetOf(awesomeProject)
        LOGGER.info { awesomeProject.hashCode().toString() }

        projectRepository.save(awesomeProject)
        LOGGER.info { awesomeProject.hashCode().toString() }

        assertTrue(awesomeProject in hashSet)
    }

    @Test
    internal fun valForIdTest() {
        val client = Client("New client")
        assertTrue(client.isNew())
        clientRepository.save(client)

        assertTrue(!client.isNew())
    }

    @Test
    internal fun lateInitWorks() {
        val project = Project().apply {
            client = Client("The Best Client")
        }
        assertTrue(project.client!!.name != null)
    }

}