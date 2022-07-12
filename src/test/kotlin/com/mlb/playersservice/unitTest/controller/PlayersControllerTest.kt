package com.mlb.playersservice.unitTest.controller

import com.mlb.playersservice.controller.PlayersController
import com.mlb.playersservice.domain.repository.Player
import com.mlb.playersservice.service.PlayersService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.util.*

@ExtendWith(MockKExtension::class)
class PlayersControllerTest {

    @MockK
    lateinit var playersService: PlayersService

    @InjectMockKs
    lateinit var playersController: PlayersController

    private lateinit var webTestClient: WebTestClient

    @BeforeEach
    internal fun setUp() {
        webTestClient = WebTestClient.bindToController(playersController).build()
    }

    @Test
    fun `returns player stats when service returns player object`() {

        val playerId = UUID.randomUUID()
        every { playersService.getPlayerStats(any()) } returns Mono.just(Player(playerId))

        webTestClient
            .get()
            .uri("/players/${playerId}/stats")
            .exchange()
            .expectStatus()
            .isOk
            .expectBody()
            .jsonPath("$.playerId")
            .isEqualTo(playerId.toString())

        verify(exactly = 1) { playersService.getPlayerStats(playerId) }

    }
}