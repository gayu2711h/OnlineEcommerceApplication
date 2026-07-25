package com.ecom.service;

import com.ecom.dto.ProductRequestDto;
import com.ecom.dto.ProductResponseDto;

import jakarta.validation.Valid;

public interface ProductService 
{

	ProductResponseDto addProduct(@Valid ProductRequestDto productDto);	
}
