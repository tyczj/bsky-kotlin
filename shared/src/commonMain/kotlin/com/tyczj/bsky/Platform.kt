package com.tyczj.bsky

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform