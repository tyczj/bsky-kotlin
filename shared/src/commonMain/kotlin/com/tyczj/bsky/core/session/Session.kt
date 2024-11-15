package com.tyczj.bsky.core.session

import com.tyczj.bsky.core.client.BaseHttpClient
import com.tyczj.bsky.core.data.SessionData
import com.tyczj.bsky.core.data.session.SessionRequestData
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.URLBuilder
import io.ktor.http.contentType
import io.ktor.util.reflect.typeInfo

class Session (val service: String, val handle: String, val appPassword: String) {

    val client = BaseHttpClient.instance

    var sessionData: SessionData? = null

    suspend fun createSession(): SessionData?{
        val builder = HttpRequestBuilder()
        builder.url(URLBuilder("https://$service/xrpc/com.atproto.server.createSession").build())
        builder.method = HttpMethod.Post
        builder.contentType(ContentType.Application.Json)
        builder.setBody(SessionRequestData(handle, appPassword))
        return client.post(builder, typeInfo<SessionData>())
    }

    suspend fun refreshSession(){

    }

    suspend fun deleteSession(){

    }

    suspend fun getSession(){

    }
}