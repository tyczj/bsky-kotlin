package com.tyczj.bsky.core.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.reflect.TypeInfo
import kotlinx.serialization.json.Json

class BaseHttpClient private constructor() {

    companion object{
        internal val instance: BaseHttpClient by lazy { BaseHttpClient() }
    }

    private val _client: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                this.isLenient = true
                this.ignoreUnknownKeys = true
            })
        }
    }

    /**
     * Get
     *
     * @param T
     * @param builder
     * @return
     */
    suspend fun <T> get(builder: HttpRequestBuilder, typeInfo: TypeInfo): T? {
        val response = _client.get(builder)

//        when (response.status.value) {
//            in 300..399 -> throw RedirectResponseException(response, response.bodyAsText())
//            in 400..499 -> throw ClientRequestException(response, response.bodyAsText())
//            in 500..599 -> throw ServerResponseException(response, response.bodyAsText())
//        }
//
//        if (response.status.value >= 600) {
//            throw ResponseException(response, response.bodyAsText())
//        }

        return response.body(typeInfo)
    }

//    /**
//     * Get stream
//     *
//     * @param builder
//     * @return
//     */
//    suspend fun getStream(builder: HttpRequestBuilder): HttpStatement {
//        return _client.get(builder).
//
//    }

    /**
     * Post
     *
     * @param T
     * @param builder
     * @return
     */
    suspend fun <T> post(builder: HttpRequestBuilder, typeInfo: TypeInfo): T? {
        val response = _client.post(builder)

//        when (response.status.value) {
//            in 300..399 -> throw RedirectResponseException(response, response.bodyAsText())
//            in 400..499 -> throw ClientRequestException(response, response.bodyAsText())
//            in 500..599 -> throw ServerResponseException(response, response.bodyAsText())
//        }
//
//        if (response.status.value >= 600) {
//            throw ResponseException(response, response.bodyAsText())
//        }

        return response.body(typeInfo)
    }

    /**
     * Delete
     *
     * @param T
     * @param builder
     * @return
     */
    suspend fun <T> delete(builder: HttpRequestBuilder, typeInfo: TypeInfo): T? {
        val response = _client.delete(builder)

//        when (response.status.value) {
//            in 300..399 -> throw RedirectResponseException(response, response.bodyAsText())
//            in 400..499 -> throw ClientRequestException(response, response.bodyAsText())
//            in 500..599 -> throw ServerResponseException(response, response.bodyAsText())
//        }
//
//        if (response.status.value >= 600) {
//            throw ResponseException(response, response.bodyAsText())
//        }

        return response.body(typeInfo)
    }

    /**
     * Put
     *
     * @param T
     * @param builder
     * @return
     */
    suspend fun <T> put(builder: HttpRequestBuilder, typeInfo: TypeInfo): T? {
        val response = _client.put(builder)

//        when (response.status.value) {
//            in 300..399 -> throw RedirectResponseException(response, response.bodyAsText())
//            in 400..499 -> throw ClientRequestException(response, response.bodyAsText())
//            in 500..599 -> throw ServerResponseException(response, response.bodyAsText())
//        }
//
//        if (response.status.value >= 600) {
//            throw ResponseException(response, response.bodyAsText())
//        }

        return response.body(typeInfo)
    }

    /**
     * Close
     *
     */
    fun close() {
        _client.close()
    }
}