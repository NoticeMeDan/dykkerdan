package com.noticemedan.dykkerdan.webservice.dao

import com.noticemedan.dykkerdan.webservice.entity.Dive
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Transactional(propagation = Propagation.MANDATORY)
interface DiveDao : JpaRepository<Dive, Long>, QuerydslPredicateExecutor<Dive>