package com.nirajtiwari.covid19tracker.Model

import kotlinx.serialization.Serializable

@Serializable
data class Tracking(
    val state: String,
    val total: Int,
    val positive: Int,
    val death: Int,
    val hospitalized: Int?
)
