package com.noticemedan.dykkerdan.webservice.controller

import com.noticemedan.dykkerdan.webservice.dto.DiverInfo
import com.noticemedan.dykkerdan.webservice.service.DiverService
import dk.acto.auth.client.JwtAuthentication
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("diver")
@PreAuthorize("isAuthenticated()")
class DiverController (private val diverService: DiverService) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getMyDiver(authentication: JwtAuthentication): DiverInfo = diverService.getDiverByEmail(authentication.username)

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createDiver(@RequestBody diver: DiverInfo, authentication: JwtAuthentication): DiverInfo {
        return diverService.createDiver(diver, authentication.username, authentication.name)
    }
}