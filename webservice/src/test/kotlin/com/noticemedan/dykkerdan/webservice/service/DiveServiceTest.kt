package com.noticemedan.dykkerdan.webservice.service

import com.noticemedan.dykkerdan.webservice.TestConfig
import com.noticemedan.dykkerdan.webservice.dto.DiveInfo
import com.noticemedan.dykkerdan.webservice.dto.DiverInfo
import com.noticemedan.dykkerdan.webservice.exception.DiverNotFound
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.Instant
import java.time.temporal.ChronoUnit

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [TestConfig::class])
class DiveServiceTest {
    @Autowired
    private lateinit var diveService: DiveService

    @Autowired
    private lateinit var diverService: DiverService

    private fun createTestDiver (diverEmail: String): DiverInfo {
        return diverService.createDiver(DiverInfo(), diverEmail, "test")
    }

    private fun createTestDive (diverEmail: String): DiveInfo {
        return diveService.createDive(DiveInfo(
                buddyName = "testBuddy",
                diveNumber = 42,
                date = Instant.now(),
                location = "Månen",
                timeIn = Instant.now(),
                timeOut = Instant.now().plus(1, ChronoUnit.HOURS),
                startPressure = 200,
                endPressure = 70,
                maxDepth = 14,
                weight = 12,
                comment = "Smart smart, god kommentar",
                saltWater = true,
                freshWater = false,
                shoreDive = true,
                boatDive = false,
                visibility = "Omkring 5 meter",
                airTemp = 21,
                surfaceTemp = 20,
                bottomTemp = 14
        ), diverEmail)
    }

    @Test
    fun createDive () {
        val diver = createTestDiver("test1")
        val dive = createTestDive(diver.email!!)

        assertThat(dive).isNotNull
    }

    @Test
    fun getDives () {
        val diver = createTestDiver("test2")
        createTestDive(diver.email!!)
        createTestDive(diver.email!!)

        val result = diveService.getDives(diver.email!!)

        assertThat(result).isNotNull
        assertThat(result.size).isEqualTo(2)
    }

    @Test
    fun getDive () {
        val diver = createTestDiver("test3")
        val dive = createTestDive(diver.email!!)

        val result = diveService.getDive(dive.id!!, diver.email!!)

        assertThat(result).isNotNull
        assertThat(result.id).isEqualTo(dive.id!!)
    }

    @Test
    fun updateDive () {
        val diver = createTestDiver("test4")
        val dive = createTestDive(diver.email!!)

        dive.maxDepth = 42
        val result = diveService.updateDive(dive, diver.email!!)

        assertThat(result).isNotNull
        assertThat(result.id).isEqualTo(dive.id)
        assertThat(result.maxDepth).isEqualTo(42)
    }
}