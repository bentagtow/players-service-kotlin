package com.mlb.playersservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlayersServiceApplication

fun main(args: Array<String>) {
	runApplication<PlayersServiceApplication>(*args)
}
