package com.just.cse.digitaldiary.twozerotwothree.core.di.auth

import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        AuthComponentProvider.updateAuthToken()
    }
}