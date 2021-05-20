package com.jpabuddy.kotlinentities

import javax.persistence.*

@Table(name = "client")
@Entity
open class Client {
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null

    @Column(name = "name")
    open var name: String? = null

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    open var projects: MutableList<Project>? = mutableListOf()
}