package com.mz.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class AuthClientApplication

fun main(args: Array<String>) {
    runApplication<AuthClientApplication>(*args)
	println("Starting this Kotlin application - AuthClientApplication")
}
