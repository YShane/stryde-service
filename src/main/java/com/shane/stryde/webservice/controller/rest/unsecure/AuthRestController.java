package com.shane.stryde.webservice.controller.rest.unsecure;

import java.util.Base64;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.shane.stryde.webservice.dto.UserDto;
import com.shane.stryde.webservice.dto.auth.LoginDto;
import com.shane.stryde.webservice.dto.auth.RegistrationDto;
import com.shane.stryde.webservice.scheduled.event.OnRegistrationCompleteEvent;
import com.shane.stryde.webservice.service.AuthService;
import com.shane.stryde.webservice.service.EmailVerificationTokenService;

@RestController
@RequestMapping("/auth")
public class AuthRestController {

	@Value("${base.url}")
	private String baseUrl;

	@Value("${base.url}" + "${server.servlet.contextPath}" + "/oauth/authorize}")
	private String authorizeUrl;

	@Value("${base.url}" + "${server.servlet.contextPath}" + "/oauth/token")
	private String tokenUrl;

	@Value("${oauth2.stryde.client-id}")
	private String oauth2ClientId;

	@Value("${oauth2.stryde.client-secret}")
	private String oauth2ClientSecret;

	@Value("${oauth2.stryde.resource-id")
	private String oauth2ResourceId;

	@Value("${oauth2.stryde.scope}")
	private String oauth2Scope;
	
	@Value("${app.url}")
	private String appUrl;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private AuthService authService;
	
	@Autowired
	private EmailVerificationTokenService emailVerificationTokenService;

	@PostMapping(value = "/register")
	public ResponseEntity<Object> registerUser(@RequestBody RegistrationDto registrationDto) throws Exception {
		UserDto userDto = authService.registerUser(registrationDto);
		
		// on success, publish event
		if(userDto != null) {
			eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userDto,Locale.getDefault()));
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) throws Exception {
		// retrieve token from oauth2 endpoint
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = buildHeader();
		UriComponentsBuilder builder = buildUriWithParam(loginDto);
		HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
		ResponseEntity<Object> oauthToken = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, requestEntity, Object.class);
		return new ResponseEntity<Object>(oauthToken.getBody(), HttpStatus.OK);
	}

	@GetMapping(value = "/verify/{token}")
	public void verifyEmail(@PathVariable String token, HttpServletResponse response) throws Exception {
		emailVerificationTokenService.verifyToken(token);
		response.sendRedirect(appUrl);
	}
	
	private UriComponentsBuilder buildUriWithParam(LoginDto loginDto) {
		return UriComponentsBuilder.fromHttpUrl(tokenUrl)
		        .queryParam("grant_type", "password")
		        .queryParam("username", loginDto.getUsername())
		        .queryParam("password", loginDto.getPassword())
		        .queryParam("scope", oauth2Scope);
	}

	private HttpHeaders buildHeader() {
		String clientIdSecretString = oauth2ClientId + ":" + oauth2ClientSecret; 	
		String encodedString = Base64.getEncoder().encodeToString(clientIdSecretString.getBytes());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + encodedString);
		return headers;
	}
}