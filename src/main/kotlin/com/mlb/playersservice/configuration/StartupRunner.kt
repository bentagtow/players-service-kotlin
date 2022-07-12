package com.mlb.playersservice.configuration

import com.mlb.playersservice.domain.repository.*
import com.mlb.playersservice.repository.PlayersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.util.*

@Component
class StartupRunner : CommandLineRunner {

    @Autowired
    lateinit var playersRepository: PlayersRepository

    override fun run(vararg args: String?) {
        playersRepository.deleteAll()
        playersRepository.save(createPlayer())
    }
}

val uuid: UUID = UUID.fromString("c58b1f54-0236-11ed-b939-0242ac120002")

fun createPlayer(): Player {
    return Player(
        playerId = uuid,
        name = "Sammy Sosa",
        positions = listOf(Position(UUID.randomUUID(), PositionType.RF)),
        seasons = listOf(
            PlayerSeason(
                year = 1998,
                salary = 10000000,
                teamIds = listOf(UUID.randomUUID()),
                hittingStats = HittingStats(
                    battingAverage = 0.300F
                )
            )
        )
    )
}