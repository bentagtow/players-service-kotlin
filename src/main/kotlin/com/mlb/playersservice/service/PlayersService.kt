package com.mlb.playersservice.service

import com.mlb.playersservice.domain.repository.Player
import com.mlb.playersservice.repository.PlayersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class PlayersService(@Autowired private val playersRepository: PlayersRepository) {
    fun getPlayerStats(playerId: UUID): Mono<Player?> {
        return playersRepository.findById(playerId)
    }
}
