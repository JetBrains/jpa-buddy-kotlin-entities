package com.jpabuddy.kotlinentities

import javax.persistence.*

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