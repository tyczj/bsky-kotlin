package com.tyczj.bsky.core.data.session

import kotlinx.serialization.Serializable

@Serializable
data class SessionRequestData(val identifier: String, val password: String, val authFactorToken: String? = null)
