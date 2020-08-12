package com.noticemedan.dykkerdan.webservice.service

import com.noticemedan.dykkerdan.webservice.dao.DiveDao
import com.noticemedan.dykkerdan.webservice.dto.DiveInfo
import com.noticemedan.dykkerdan.webservice.entity.Dive
import com.noticemedan.dykkerdan.webservice.util.DtoFactory
import io.vavr.kotlin.Try
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.InvalidClassException
import java.lang.Exception

@Service
class DiveService(private val diveDao: DiveDao, private val dtoFactory: DtoFactory){
    @Transactional
    fun createDive(source: DiveInfo): DiveInfo{

        val dive = Try {Dive(source.id, source.date, source.altitudes, source.duration, source.distance, source.note)}
                .getOrElseThrow { -> Exception() } //TODO: Better exception, lulw

        diveDao.save(dive)

        return dtoFactory.toInfo(dive)
    }

}
