package com.noticemedan.dykkerdan.webservice.entity

import javax.persistence.*

@Entity
data class Diver (
        var email: String,
        var name: String,
        var phone: String? = null,
        var weight: Int? = null,
        @OneToMany(mappedBy = "diver", cascade = [CascadeType.ALL], orphanRemoval = true)
        var dives: List<Dive>? = null,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null
)