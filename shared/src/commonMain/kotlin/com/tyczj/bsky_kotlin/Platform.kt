package com.tyczj.bsky_kotlin

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform