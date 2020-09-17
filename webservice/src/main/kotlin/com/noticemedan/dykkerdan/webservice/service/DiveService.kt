package com.noticemedan.dykkerdan.webservice.service

import com.noticemedan.dykkerdan.webservice.dao.DiveDao
import com.noticemedan.dykkerdan.webservice.dao.DiverDao
import com.noticemedan.dykkerdan.webservice.dto.DiveInfo
import com.noticemedan.dykkerdan.webservice.dto.DiveOverviewInfo
import com.noticemedan.dykkerdan.webservice.entity.Dive
import com.noticemedan.dykkerdan.webservice.entity.QDive
import com.noticemedan.dykkerdan.webservice.entity.QDiver
import com.noticemedan.dykkerdan.webservice.exception.DiveNotFound
import com.noticemedan.dykkerdan.webservice.exception.DiverNotFound
import com.noticemedan.dykkerdan.webservice.exception.Unauthorized
import com.noticemedan.dykkerdan.webservice.util.DtoFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class DiveService (
        private val diveDao: DiveDao,
        private val diverDao: DiverDao,
        private val dtoFactory: DtoFactory
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun createDive (source: DiveInfo, diverEmail: String): DiveInfo {
        val diver = diverDao.findOne(QDiver.diver.email.eq(diverEmail))
                .orElseThrow { DiverNotFound() }

        val dive = Dive(diver,
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
                source.bottomTemp)

        return dtoFactory.toInfo(diveDao.save(dive))
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun getDives (diverEmail: String): List<DiveOverviewInfo> {
        return diveDao.findAll(QDive.dive.diver.email.eq(diverEmail)).map(dtoFactory::toOverviewInfo)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun getDive (id: Long, diverEmail: String): DiveInfo {
        val dive = diveDao.findOne(QDive.dive.id.eq(id)).orElseThrow { DiveNotFound() }
        if (dive.diver.email != diverEmail) throw Unauthorized()
        return dtoFactory.toInfo(dive)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun updateDive (source: DiveInfo, diverEmail: String): DiveInfo {
        var dive = diveDao.findOne(QDive.dive.id.eq(source.id)).orElseThrow { DiveNotFound() }
        if (dive.diver.email != diverEmail) throw Unauthorized()
        val diver = diverDao.findOne(QDiver.diver.email.eq(diverEmail)).orElseThrow { DiverNotFound() }

        dive = Dive(diver,
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
                source.id)

        return dtoFactory.toInfo(diveDao.save(dive))
    }
}