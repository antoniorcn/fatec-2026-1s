package edu.curso

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform