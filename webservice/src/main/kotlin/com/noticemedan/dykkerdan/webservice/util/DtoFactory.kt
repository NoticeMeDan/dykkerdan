package com.noticemedan.dykkerdan.webservice.util

import com.noticemedan.dykkerdan.webservice.dto.DiveInfo
import com.noticemedan.dykkerdan.webservice.entity.Dive
import org.springframework.stereotype.Component

@Component
object DtoFactory {
    fun toInfo(source: Dive): DiveInfo = DiveInfo(
            source.date,
            source.altitudes,
            source.duration,
            source.distance,
            source.note,
            source.id
    )
}