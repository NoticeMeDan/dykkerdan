package com.noticemedan.dykkerdan.webservice.util

import com.noticemedan.dykkerdan.webservice.dto.DiveInfo
import com.noticemedan.dykkerdan.webservice.dto.DiveOverviewInfo
import com.noticemedan.dykkerdan.webservice.dto.DiverInfo
import com.noticemedan.dykkerdan.webservice.entity.Dive
import com.noticemedan.dykkerdan.webservice.entity.Diver
import org.springframework.stereotype.Component

@Component
object DtoFactory {
    fun toInfo(source: Diver): DiverInfo =
            DiverInfo(
                    source.email,
                    source.name,
                    source.phone,
                    source.weight)

    fun toInfo(source: Dive): DiveInfo =
            DiveInfo(
                    source.buddyName,
                    source.diveNumber,
                    source.date,
                    source.location,
                    source.timeIn,
                    source.timeOut,
                    source.startPressure,
                    source.endPressure,
                    source.maxDepth,
                    source.weight,
                    source.comment,
                    source.saltWater,
                    source.freshWater,
                    source.shoreDive,
                    source.boatDive,
                    source.visibility,
                    source.airTemp,
                    source.surfaceTemp,
                    source.bottomTemp,
                    source.id
            )

    fun toOverviewInfo (source: Dive): DiveOverviewInfo =
            DiveOverviewInfo(
                    source.diveNumber,
                    source.date,
                    source.location,
                    source.timeIn,
                    source.timeOut
            )
}