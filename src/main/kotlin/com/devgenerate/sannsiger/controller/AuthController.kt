package com.devgenerate.sannsiger.controller

import com.devgenerate.sannsiger.config.JwtUtil
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController {

    @PostMapping("/login")
    fun login(@RequestParam username: String): Map<String, String> {
        val token = JwtUtil.generateToken(username)
        return mapOf("token" to token)
    }
}