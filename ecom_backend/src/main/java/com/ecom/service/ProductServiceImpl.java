package com.ecom.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.custom_exception.ApiException;
import com.ecom.custom_exception.ResourceNotFoundException;
import com.ecom.dtos.ProductRequestDto;
import com.ecom.dtos.ProductResponseDto;
import com.ecom.entities.Category;
import com.ecom.entities.Product;
import com.ecom.repository.CategoryRepository;
import com.ecom.repository.ProductRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService
{
	private final ProductRepository productRepository;
	
	private final CategoryRepository categoryRepository;
	
	private final ModelMapper mapper;

	@Override
	public ProductResponseDto addProduct(@Valid ProductRequestDto productDto) 
	{
		// Check Duplicate Product
	     if (productRepository.existsByProductNameIgnoreCase(productDto.getProductName())) 
	     {
	    	 throw new ApiException(
              "Product already exists with name : " + productDto.getProductName());
	     }
	        
	     // Fetch Category
	     Category category = categoryRepository.findById(productDto.getCategoryId())
	    		 							.orElseThrow(() -> new ResourceNotFoundException(
	    		 							"Category", "categoryId", productDto.getCategoryId()));

	    // DTO -> Entity
	    Product product = mapper.map(productDto, Product.class);

	    // Set Category
	    product.setCategory(category);

	   // Calculate Special Price
	    product.setSpecialPrice(
	    productDto.getPrice() - ((productDto.getPrice() * productDto.getDiscount()) / 100));

	   // Save Product
	    Product savedProduct = productRepository.save(product);

	   // Entity -> DTO
	    ProductResponseDto response = mapper.map(savedProduct, ProductResponseDto.class);

	   // ModelMapper cannot map nested objects automatically
	    response.setCategoryId(savedProduct.getCategory().getCategoryId());
	    response.setCategoryName(savedProduct.getCategory().getCategoryName());

	    return response;
	}
}
