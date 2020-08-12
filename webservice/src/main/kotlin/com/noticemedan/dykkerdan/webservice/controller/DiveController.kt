package com.noticemedan.dykkerdan.webservice.controller

import com.noticemedan.dykkerdan.webservice.dto.DiveInfo
import com.noticemedan.dykkerdan.webservice.service.DiveService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class DiveController (private val diveService: DiveService){
    @PostMapping(produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createDive(@RequestBody dive: DiveInfo): DiveInfo {
        return diveService.createDive(dive)
    }

    @GetMapping(value = ["{diveId}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getDive(@PathVariable diveId:Long) : DiveInfo{
        return diveService.getDive(diveId)
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateDive(@RequestBody dive: DiveInfo) : DiveInfo{
        return diveService.updateDive(dive)
    }

    @DeleteMapping("{diveId}")
    fun deleteDive(@PathVariable diveId:Long) : Long {
        return diveService.deleteDive(diveId)
    }
}