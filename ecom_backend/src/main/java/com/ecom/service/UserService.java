package com.ecom.service;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.UserSignUpReqDto;

public interface UserService {

	//method to sign up
	ApiResponse registerUser(UserSignUpReqDto dto);

	
}
