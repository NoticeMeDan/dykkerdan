package com.noticemedan.dykkerdan.webservice.dto

import java.time.Instant

data class DiveOverviewInfo(
        var diveNumber: Int? = null,
        var date: Instant? = null,
        var location: String? = null,
        var timeIn: Instant? = null,
        var timeOut: Instant? = null
)