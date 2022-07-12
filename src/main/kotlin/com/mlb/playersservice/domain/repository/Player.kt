package com.mlb.playersservice.domain.repository

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(value = "players")
data class Player(
    @Id
    val playerId: UUID,
    val name: String? = null,
    val positions: List<Position>? = null,
    val seasons: List<PlayerSeason>? = null
)

data class PlayerSeason(
    val year: Int? = null,
    val salary: Long? = null,
    val teamIds: List<UUID>? = null,
    val hittingStats: HittingStats? = null,
    val fieldingStats: FieldingStats? = null,
    val pitchingStats: PitchingStats? = null
)

data class PitchingStats (
    val era: Float? = null
)

data class FieldingStats (
    val errors: Int? = null
)

data class HittingStats (
    val battingAverage: Float? = null
)

data class Position (
    val id: UUID,
    val name: PositionType? = null
)

enum class PositionType {
    PITCHER,
    FIRST,
    SECOND,
    THIRD,
    CATCHER,
    SHORT_STOP,
    CF,
    LF,
    RF
}

