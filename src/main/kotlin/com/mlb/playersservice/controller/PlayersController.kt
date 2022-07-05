package com.mlb.playersservice.controller

import com.mlb.playersservice.domain.repository.Player
import com.mlb.playersservice.service.PlayersService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class PlayersController constructor(private val playersService: PlayersService){


    @GetMapping("/players/{playerId}/stats")
    fun getPlayerStats(@PathVariable playerId: UUID): ResponseEntity<Mono<Player?>> {

        return ResponseEntity.ok(playersService.getPlayerStats(playerId))

    }

}