package com.mlb.playersservice.domain.repository

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(value = "teams")
data class Team(
    @Id
    val id: UUID,
    val name: String
)
