package com.jpabuddy.kotlinentities

import java.math.BigDecimal
import javax.persistence.*

@Table(name = "client")
@Entity
data class Client(
    @Column(name = "id", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,

    @Column(name = "name", nullable = false)
    var name: String,

    @OneToMany(mappedBy = "client", orphanRemoval = true)
    var projects: MutableSet<Project> = mutableSetOf(),

    @JoinColumn(name = "client_id")
    @OneToMany
    var contacts: MutableSet<Contact> = mutableSetOf(),
)


/*@Entity
class ValuableClient(override var id: Long?): Client(id) {

    @Column(name = "turnover", precision = 19, scale = 2)
    var turnover: BigDecimal? = null
}*/

