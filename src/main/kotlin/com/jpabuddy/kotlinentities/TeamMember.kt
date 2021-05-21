package com.jpabuddy.kotlinentities

import javax.persistence.*


@Entity
@Table(name = "team_member")
open class TeamMember {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    open var id: Long? = null

    @Column(name = "name", nullable = false)
    open lateinit var name: String

    @JoinTable(
        name = "team_member_team_link",
        joinColumns = [JoinColumn(name = "team_member_id")],
        inverseJoinColumns = [JoinColumn(name = "team_id")]
    )
    @ManyToMany
    open var teams: MutableList<Team>? = mutableListOf()

}