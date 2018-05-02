package com.stryde.webservice.controller.rest.secured;

import java.io.IOException;
import java.text.ParseException;

import javax.validation.Valid;

import com.stryde.webservice.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.stryde.webservice.dto.UserDto;
import com.stryde.webservice.service.AccountService;
import com.stryde.webservice.service.AuthService;

@RestController
@RequestMapping("/account")
public class AccountRestController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AuthService AuthService;

	@GetMapping
	public UserDto getCurrentUser(Authentication authentication) {
		return AuthService.getCurrentUserByAuthentication(authentication);
	}
	
	@PreAuthorize("@securityService.isMyAccount(#authentication, #userDto.userId)")
	@PostMapping
	public UserDto updateCurrentUser(@Valid @RequestBody UserDto userDto) throws ParseException {
		return accountService.updateUser(userDto);
	}
	
	@PreAuthorize("@securityService.isMyAccount(#authentication, #userDto.userId)")
	@PostMapping("/upload/profile")
	@ResponseStatus(HttpStatus.OK)
	public void updateCurrentUserProfilePicture( @RequestParam("extraField") String extraField,
            @RequestParam("file") MultipartFile multipartFile, @Valid @RequestBody UserDto userDto) throws ParseException, IOException {
		accountService.updateProfilePicture(userDto, multipartFile);
	}
}
