package com.noticemedan.dykkerdan.webservice.entity

import org.springframework.context.annotation.Primary
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Dive (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        val date: Date,
        val minAltitude: Float,
        val maxAltitude: Float,
        val distance: Float,
        val note: String?
)