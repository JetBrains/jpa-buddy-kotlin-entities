package com.jpabuddy.kotlinentities

import javax.persistence.*

@Entity
@Table(name = "team")
open class Team {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    open var id: Long? = null

    @Column(name = "name")
    open var name: String? = null

    @JoinColumn(name = "project_id")
    @OneToOne(fetch = FetchType.LAZY)
    open var project: Project? = null

    @ManyToMany(mappedBy = "teams")
    open var teamMember: MutableList<TeamMember>? = mutableListOf()
}
