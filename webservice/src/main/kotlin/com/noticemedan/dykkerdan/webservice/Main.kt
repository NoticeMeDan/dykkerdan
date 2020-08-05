package com.noticemedan.dykkerdan.webservice

import dk.acto.auth.client.FafnirClientConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Import


@SpringBootApplication
@Import(FafnirClientConfiguration::class)
class WebserviceApplication

fun main(args: Array<String>) {
	runApplication<WebserviceApplication>(*args)
}
