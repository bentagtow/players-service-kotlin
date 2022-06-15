package com.mlb.playersservice.integrationTest

import com.mlb.playersservice.domain.repository.Player
import com.mlb.playersservice.repository.PlayersRepository
import com.mlb.playersservice.testConfig.DbContainerConfig
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.*

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("testing")
@Testcontainers
class PlayersServiceIntegrationTest @Autowired constructor(
    private val playersRepository: PlayersRepository,
    private val webTestClient: WebTestClient
) {
    companion object {
        @Container
        private val mongoContainer = DbContainerConfig.getInstance()

        @DynamicPropertySource
        @JvmStatic
        fun mongoDbProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.mongodb.uri", mongoContainer::getReplicaSetUrl)
        }
    }

    @BeforeEach
    fun setUp() {
        playersRepository.save(
            createPlayer()
        ).subscribe()
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