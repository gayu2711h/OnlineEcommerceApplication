package com.ecom.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.custom_exception.ApiException;
import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.UserSignUpReqDto;
import com.ecom.entities.UserEntity;
import com.ecom.enums.Role;
import com.ecom.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ModelMapper mapper;
	private final PasswordEncoder passwordEncoder;
	
	@Transactional
	@Override
	public ApiResponse registerUser(UserSignUpReqDto dto) {
		
		//validate for duplicate userName or email -> unique filed in database
		if(userRepository.existsByEmail(dto.getEmail())) {
		    throw new ApiException("Email already registered.");
		}

		if(userRepository.existsByUserName(dto.getUserName())) {
		    throw new ApiException("Username already exists.");
		}
		
		//in case of no duplicate userName or email  
		//you need to convert-> DTO -> Entity (Hibernate doesn't understand what is DTO )
		UserEntity entity = mapper.map(dto, UserEntity.class);
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		
		//assign User Role (ROLE_CUSTOMER)
		entity.setRole(Role.ROLE_CUSTOMER);
		
	    UserEntity persitEntity	=userRepository.save(entity);//save entity
		
		return new ApiResponse("Success","User Registered with Id "+persitEntity.getUserId()+"!!");
	}

}
