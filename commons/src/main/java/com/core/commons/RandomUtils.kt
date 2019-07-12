package com.core.commons

private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

object RandomUtils {

    fun generateString(size: Int) = (1..size)
            .map { kotlin.random.Random.nextInt(0, charPool.size) }
            .map(charPool::get)
            .joinToString("")
}
