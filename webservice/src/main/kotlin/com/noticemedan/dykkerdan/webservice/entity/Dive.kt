package com.noticemedan.dykkerdan.webservice.entity

import java.time.Instant
import javax.persistence.*

@Entity
data class Dive (
        @ManyToOne
        @JoinColumn(name = "diver_id")
        var diver: Diver,
        var buddyName: String? = null,
        var diveNumber: Int? = null,
        var date: Instant? = null,
        var location: String? = null,
        var timeIn: Instant? = null,
        var timeOut: Instant? = null,
        var startPressure: Int? = null,
        var endPressure: Int? = null,
        var maxDepth: Int? = null,
        var weight: Int? = null,
        var comment: String? = null,
        var saltWater: Boolean = false,
        var freshWater: Boolean = false,
        var shoreDive: Boolean = false,
        var boatDive: Boolean = false,
        var visibility: String? = null,
        var airTemp: Int? = null,
        var surfaceTemp: Int? = null,
        var bottomTemp: Int? = null,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null
)