package com.mlb.playersservice.integrationTest

import com.mlb.playersservice.domain.repository.Player
import com.mlb.playersservice.repository.PlayersRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import java.util.*

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("testing")
class PlayersServiceIntegrationTest @Autowired constructor(
    private val playersRepository: PlayersRepository,
    private val webTestClient: WebTestClient
) {

    @BeforeEach
    fun setUp() {
        playersRepository.save(
            createPlayer()
        ).block()
    }

    @Test
    fun `Returns career stats for given player`() {
        webTestClient.get().uri("players/${uuid}/stats")
            .exchange()
            .expectStatus().isOk
    }
}

val uuid: UUID = UUID.randomUUID()

fun createPlayer(): Player {
    return Player(id = uuid)
}