package com.mz.auth.controller

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.mz.auth.model.User
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.ModelAndView
import java.util.Arrays
import java.util.Base64

@RestController
@RequestMapping("user")
class UserController {

	/**
	 * This endpoint returns a jsp page.
	 */
	@GetMapping("/getUsers")
	fun getUsers(): ModelAndView {
		println("Client - UserController - getUsers()")
		return ModelAndView("Users")
	}

	/**
	 * This endpoint is to get the Authorization code as a request parameter.
	 * Then using this authorization code we get the Access Token.
	 * @param String code.
	 */
	@GetMapping("/showUsers")
	fun showUsers(@RequestParam(value = "code") code: String): ModelAndView? {
		println("Client - UserController - showUsers()")
		val responseEntity: ResponseEntity<String>
		println("Authorization code: $code")

		val restTemplate: RestTemplate = RestTemplate()

		val encodedCredentials: String = Base64.getEncoder().encodeToString("client123".toByteArray())

		val headers: HttpHeaders = HttpHeaders()
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON))
		headers.add("Authorization", "Basic " + encodedCredentials)

		val request: HttpEntity<String> = HttpEntity<String>(headers)

		val access_token_url: String =
				"http://localhost:8080/oauth/token" + "?code=" + code + "&grant_type=authorization_code" + "&redirect_uri=http://localhost:8090/user/ShowUsers"

		responseEntity = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String::class.java)
		println("Access Toker response: " + responseEntity.getBody())
		
		// Get the Access Token From the recieved JSON response
		val mapper: ObjectMapper = ObjectMapper()
		val node: JsonNode = mapper.readTree(responseEntity.getBody())
		val token: String = node.path("access_token").asText()

		val url: String = "http://localhost:8080/user/getUsers"

		// Use the access token for authentication
		val headers1: HttpHeaders = HttpHeaders()
		headers1.add("Authorization", "Bearer " + token)
		val entity: HttpEntity<String> = HttpEntity<String>(headers1)

		val users: ResponseEntity<Array<User>> = restTemplate.exchange(url, HttpMethod.GET, entity, Array<User>::class.java)
		println(users)
		val userArray: Array<User> = users.getBody()

		val model: ModelAndView = ModelAndView("ShowUsers")
		model.addObject("employees", Arrays.asList(userArray))
		return model
	}
}