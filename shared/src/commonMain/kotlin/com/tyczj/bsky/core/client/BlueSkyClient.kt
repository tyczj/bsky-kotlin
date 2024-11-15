package com.tyczj.bsky.core.client

import com.tyczj.bsky.core.data.SessionData
import com.tyczj.bsky.core.session.Session

class BlueSkyClient(private val session: Session) {

    val client = BaseHttpClient.instance

    fun getSessionData(): SessionData?{
        return session.sessionData
    }
}