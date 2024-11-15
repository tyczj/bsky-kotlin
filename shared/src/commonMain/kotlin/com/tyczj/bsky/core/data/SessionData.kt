package com.tyczj.bsky.core.data

data class SessionData(
    val accessJwt: String,
    val active: Boolean,
    val did: String,
    val didDoc: DidDoc,
    val email: String,
    val emailAuthFactor: Boolean,
    val emailConfirmed: Boolean,
    val handle: String,
    val refreshJwt: String,
    val status: String
)