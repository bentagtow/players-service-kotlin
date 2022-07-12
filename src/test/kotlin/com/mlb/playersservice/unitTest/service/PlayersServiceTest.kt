package com.mlb.playersservice.unitTest.service

import com.mlb.playersservice.controller.PlayersController
import com.mlb.playersservice.domain.repository.Player
import com.mlb.playersservice.integrationTest.createPlayer
import com.mlb.playersservice.repository.PlayersRepository
import com.mlb.playersservice.service.PlayersService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.util.*
import java.util.UUID.*

@ExtendWith(MockKExtension::class)
class PlayersServiceTest {

    @MockK
    lateinit var playersRepository: PlayersRepository

    @InjectMockKs
    lateinit var playersService: PlayersService

    @Test
    fun `getPlayers() calls PlayerRepository and returns Player object as Mono`() {
        val testUUID = randomUUID()
        val sammySosa = createPlayer()
        every { playersRepository.findById(testUUID) } returns Mono.just(sammySosa)

        val playerStats = playersService.getPlayerStats(testUUID)

        verify(exactly = 1) { playersRepository.findById(testUUID) }

        StepVerifier.create(playerStats).expectNext(sammySosa).verifyComplete()
    }
}