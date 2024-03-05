package com.github.enteraname74.remote_data_source.model

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/**
 * HTTP Client for the application.
 */
internal class AppHttpClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }

    /**
     * Perform a GET request.
     *
     * @param url The url used for the GET request.
     * @return the found data or null.
     */
    @PublishedApi internal suspend inline fun <reified T>get(url: String): T? {
        return try {
            val response = client.get(url)

            if (!response.status.isSuccess()) {
                null
            } else {
                response.body()
            }
        } catch (_: Exception) {
            null
        }
    }

    /**
     * Perform a POST request.
     *
     * @param url The url used for the GET request.
     * @param body The body used for the POST request.
     * @return the found data or null.
     */
    @PublishedApi internal suspend inline fun <reified T>post(url: String, body: @Serializable Any): T? {
        return try {
            val response = client.post(url) {
                headers {
                    append(HttpHeaders.ContentType, "application/json; charset=utf-8")
                }
                setBody(body)
            }

            if (!response.status.isSuccess()) {
                println("STATUS ERR: $response")
                null
            } else {
                response.body()
            }
        } catch (e: Exception) {
            println("error: $e")
            null
        }
    }
}