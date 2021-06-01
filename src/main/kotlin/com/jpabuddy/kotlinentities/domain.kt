package com.jpabuddy.kotlinentities

import org.hibernate.annotations.NaturalId
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Table(name = "project")
@Entity
data class Project(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null
) {

    @Column(name = "name", nullable = false)
    lateinit var name: String

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    var client: Client? = null

    fun isNew(): Boolean = id == null

    override fun hashCode(): Int = this.javaClass.hashCode()
}


interface ProjectRepository : JpaRepository<Project, Long>

@Table(name = "client")
@Entity
open class Client(
    @Column(name = "name", nullable = false)
    open var name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null,

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    open var projects: MutableSet<Project> = mutableSetOf(),

    @JoinColumn(name = "client_id")
    @OneToMany
    open var contacts: MutableSet<Contact> = mutableSetOf(),
)

@Table(name = "contact")
@Entity
data class Contact(
    @Id
    @NaturalId
    @Column(name = "email", nullable = false)
    val email: String
) {
    @Column(name = "name")
    var name: String? = null
}

