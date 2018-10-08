package com.mz.auth.controller

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.ModelAndView
import java.util.Arrays
import java.util.Base64

@RestController
class UserController {

	@GetMapping("/getUsers")
	fun getUsers(): ModelAndView {
		return ModelAndView("Users")
	}

	@GetMapping("/showUsers")
	fun showUsers(@RequestParam("code") code: String): ModelAndView? {
		val responseEntity: ResponseEntity<String>
		println("Authorization code: $code")

		val restTemplate: RestTemplate = RestTemplate()

		val encodedCredentials: String = Base64.getEncoder().encodeToString("client123".toByteArray())

		val headers: HttpHeaders = HttpHeaders()
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON))
		headers.add("Authorization", "Basic " + encodedCredentials)

		val request: HttpEntity<String> = HttpEntity<String>(headers)

		val access_token_url: String =
				"http://localhost:8080/oauth/token" + "?code=" + code + "&grant_type=authorization_code" + "&redirect_uri=http://localhost:8090/showEmployees"

		responseEntity = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String::class.java)
		println("Access Toker response: " + responseEntity.getBody())
		return null
	}
}