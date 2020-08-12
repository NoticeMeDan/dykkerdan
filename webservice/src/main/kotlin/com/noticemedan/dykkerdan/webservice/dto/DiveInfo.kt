package com.noticemedan.dykkerdan.webservice.dto

import java.util.*

data class DiveInfo(
        val date: Date,
        val altitudes: List<Float>,
        val duration: Float,
        val distance: Float,
        val note: String,
        val id: Long?
)