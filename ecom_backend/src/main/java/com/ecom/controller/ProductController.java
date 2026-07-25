package com.ecom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dtos.ProductRequestDto;
import com.ecom.dtos.ProductResponseDto;
import com.ecom.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController 
{
	private final ProductService productService;
	
/*   ===========================
     Add Product -
     * Description : Adds a new product to the catalog.
     * URL         : POST /products
	 * Method      : POST
	 * Access      : ADMIN
	 * Request     : ProductRequestDto
	 * Response    : ProductResponseDto
     =========================== */
	
	@PostMapping
	public ResponseEntity<?> addProduct(@Valid @RequestBody ProductRequestDto productDto)
	{
		ProductResponseDto response = productService.addProduct(productDto);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
