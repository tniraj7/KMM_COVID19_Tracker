package com.nirajtiwari.covid19tracker.API

import com.nirajtiwari.covid19tracker.ApplicationDispatcher
import com.nirajtiwari.covid19tracker.Model.Tracking
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.decodeFromString

class CovidTrackingAPI {

    companion object {
        const val url = "https://covidtracking.com/api/v1/states/current.json"
        val client = HttpClient() {

            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }

    fun fetchCovidTrackingData(
        success: (List<Tracking>) -> Unit,
        failure: (Throwable?) -> Unit
    ) {

        GlobalScope.launch(ApplicationDispatcher) {
            try {
                val data = client.get<String>(url)

                kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                }.decodeFromString<List<Tracking>>(data).also(success)

            } catch (e: Exception) {
                failure(e)
                e.printStackTrace()
            }
        }
    }
}