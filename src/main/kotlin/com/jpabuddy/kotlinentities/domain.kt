package com.jpabuddy.kotlinentities

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Table(name = "project")
@Entity
data class Project(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY)
    var client: Client? = null
)

interface ProjectRepository : JpaRepository<Project, Long>

@Table(name = "client")
@Entity
data class Client(
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    var projects: MutableSet<Project> = mutableSetOf(),

    @JoinColumn(name = "client_id")
    @OneToMany
    var contacts: MutableSet<Contact> = mutableSetOf(),
)

interface ClientRepository : JpaRepository<Client, Long>

@Table(name = "contact")
@Entity
data class Contact(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    var id: Long? = null,

    @Column(name = "name")
    var name: String? = null
)

