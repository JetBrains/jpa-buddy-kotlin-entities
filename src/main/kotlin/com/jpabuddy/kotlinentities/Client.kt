package com.jpabuddy.kotlinentities

import javax.persistence.*

@Table(name = "client")
@Entity
open class Client {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null

    @Column(name = "name", nullable = false)
    open lateinit var name: String

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    open var projects: MutableList<Project>? = mutableListOf()
}