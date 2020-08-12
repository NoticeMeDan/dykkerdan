package com.noticemedan.dykkerdan.webservice.entity

import org.springframework.context.annotation.Primary
import java.util.*
import javax.persistence.*

@Entity
data class Dive (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long?,
        val date: Date,
        @ElementCollection
        val altitudes: List<Float>,
        val duration: Float,
        val distance: Float,
        val note: String
)