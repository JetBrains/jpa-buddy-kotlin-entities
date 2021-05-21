package com.jpabuddy.kotlinentities

import javax.persistence.*

@Table(name = "project")
@Entity
open class Project {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    open var id: Long? = null

    @Column(name = "name", nullable = false)
    open lateinit var name: String

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
    open var team: Team? = null

    @JoinColumn(name = "client_id")
    @ManyToOne(fetch = FetchType.LAZY)
    open var client: Client? = null
}