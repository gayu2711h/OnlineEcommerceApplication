package com.ecom.service;

import com.ecom.dtos.ProductRequestDto;
import com.ecom.dtos.ProductResponseDto;

import jakarta.validation.Valid;

public interface ProductService 
{

	ProductResponseDto addProduct(@Valid ProductRequestDto productDto);	
}
