package com.noticemedan.dykkerdan.webservice.controller

import com.noticemedan.dykkerdan.webservice.dto.DiveInfo
import com.noticemedan.dykkerdan.webservice.dto.DiveOverviewInfo
import com.noticemedan.dykkerdan.webservice.service.DiveService
import dk.acto.auth.client.JwtAuthentication
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dive")
@PreAuthorize("isAuthenticated()")
class DiveController (private val diveService: DiveService) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getDives (authentication: JwtAuthentication): List<DiveOverviewInfo> {
        return diveService.getDives(authentication.username)
    }

    @GetMapping(value = ["{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getDive(@PathVariable id: Long, authentication: JwtAuthentication): DiveInfo {
        return diveService.getDive(id, authentication.username)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createDive(@RequestBody source: DiveInfo, authentication: JwtAuthentication): DiveInfo {
        return diveService.createDive(source, authentication.username)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateDive(@RequestBody source: DiveInfo, authentication: JwtAuthentication): DiveInfo {
        return diveService.updateDive(source, authentication.username)
    }
}