package com.noticemedan.dykkerdan.webservice.service

import com.noticemedan.dykkerdan.webservice.TestConfig
import com.noticemedan.dykkerdan.webservice.dto.DiverInfo
import com.noticemedan.dykkerdan.webservice.exception.DiverNotFound
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [TestConfig::class])
class DiverServiceTest {
    @Autowired
    private lateinit var diverService: DiverService

    @Test
    fun createDiver () {
        val source = DiverInfo(phone = "88888888", weight = 42)
        val email = "ej@acto.dk"
        val name = "Elias"
        val result = diverService.createDiver(source, email, name)

        assertThat(result).isNotNull
        assertThat(result.email).isEqualTo(email)
        assertThat(result.name).isEqualTo(name)
        assertThat(result.phone).isEqualTo(source.phone)
        assertThat(result.weight).isEqualTo(source.weight)
    }

    @Test
    fun getDiver () {
        val info = diverService.createDiver(DiverInfo(phone = "123", weight = 42), "testdiver", "Eyyy")
        val result = diverService.getDiverByEmail(info.email!!)

        assertThat(result).isNotNull
        assertThat(result.email).isEqualTo(info.email)
    }

    @Test
    fun getUnknownDiver () {
        assertThrows<DiverNotFound> { diverService.getDiverByEmail("Shady's back") }
    }
}