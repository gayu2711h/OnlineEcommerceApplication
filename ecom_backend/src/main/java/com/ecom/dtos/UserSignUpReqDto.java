package com.ecom.dtos;

import com.ecom.enums.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSignUpReqDto {

	@NotBlank(message = "Username must be supplied")
    @Size(max = 20)
    private String userName;

	@NotBlank(message = "Email must be supplied")
    @Size(max = 50)
	@Email(message = "Invalid email format")
    private String email;

    @Size(max = 10)
    @NotBlank
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message = "Invalid password format")
    private String password;
    
    @NotNull(message = "user role must be supplied")
    @Enumerated(EnumType.STRING)
    private Role role;

}
