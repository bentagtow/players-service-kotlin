package com.mlb.playersservice.repository

import com.mlb.playersservice.domain.repository.Player
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlayersRepository: ReactiveMongoRepository<Player, UUID>{}