package com.ecom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dtos.UserSignUpReqDto;
import com.ecom.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserContorller {

	//Constructor based D.I
	public final UserService userService;
	
	/*
	 * Desc - User registration
	 *  URL - http://localhost:9090/users/signup
	 * Method - POST
	   Request : UserSignUpDto  @RequestBody
	   Response : ApiResponse
	   error - dup email - SC 400 | SC 409 , api-resp - error-msg
	 */
	
	@PostMapping("/signup")
	public ResponseEntity<?> signUpUser(@Valid @RequestBody UserSignUpReqDto dto)
	{
		System.out.println("in signup "+dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(dto));
	}
}
