package com.ecom.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto 
{
	@NotBlank(message = "Product name is required")
    @Size(min = 3, message = "Product name must contain at least 3 characters")
	private String productName;
	
	@NotBlank(message = "Image is required")
	private String image;
	
	@NotBlank(message = "Description is required")
    @Size(min = 6, message = "Product description must contain at least 6 characters")
	private String description;
	
	@NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
	private Integer quantity;
	
	@NotNull(message = "Price is required")
	@Positive(message = "Price must be greater than 0")
	private Double price;
	
	@Min(value = 0, message = "Discount cannot be negative")
    @Max(value = 100, message = "Discount cannot exceed 100")
	private double discount;
	
	private double specialPrice;
	
	@NotNull(message = "Category Id is required")
	private Long categoryId;
}
