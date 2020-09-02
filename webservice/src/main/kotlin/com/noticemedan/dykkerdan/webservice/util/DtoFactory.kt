package com.noticemedan.dykkerdan.webservice.util

import com.noticemedan.dykkerdan.webservice.dto.DiverInfo
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
}