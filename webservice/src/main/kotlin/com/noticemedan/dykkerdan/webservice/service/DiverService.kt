package com.noticemedan.dykkerdan.webservice.service

import com.noticemedan.dykkerdan.webservice.dao.DiverDao
import com.noticemedan.dykkerdan.webservice.dto.DiverInfo
import com.noticemedan.dykkerdan.webservice.entity.Diver
import com.noticemedan.dykkerdan.webservice.entity.QDiver
import com.noticemedan.dykkerdan.webservice.exception.DiverNotFound
import com.noticemedan.dykkerdan.webservice.util.DtoFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class DiverService (private val diverDao: DiverDao, private val dtoFactory: DtoFactory) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun getDiverByEmail (email: String): DiverInfo {
        return diverDao.findOne(QDiver.diver.email.eq(email))
                .map(dtoFactory::toInfo)
                .orElseThrow { DiverNotFound() }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun createDiver (source: DiverInfo, email: String, name: String): DiverInfo {
        val diver = diverDao.save(Diver(email, name, source.phone, source.weight))
        return dtoFactory.toInfo(diver)
    }
}