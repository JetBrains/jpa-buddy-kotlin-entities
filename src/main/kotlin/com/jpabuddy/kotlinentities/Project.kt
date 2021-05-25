package com.jpabuddy.kotlinentities

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