package com.nirajtiwari.covid19tracker.Model

import kotlinx.serialization.Serializable

@Serializable
data class TrackingList(
    val list: ArrayList<Tracking>
)
