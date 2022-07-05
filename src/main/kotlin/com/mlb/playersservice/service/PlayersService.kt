package com.mlb.playersservice.service

import com.mlb.playersservice.domain.repository.Player
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class PlayersService {

    fun getPlayerStats(playerId: UUID): Mono<Player?> {

        return Mono.empty()

    }
}
