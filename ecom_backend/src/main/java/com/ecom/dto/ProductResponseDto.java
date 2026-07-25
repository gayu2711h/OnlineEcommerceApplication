package com.ecom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {

    private Long productId;

    private String productName;

    private String image;

    private String description;

    private Integer quantity;

    private Double price;

    private double discount;

    private double specialPrice;

    private Long categoryId;

    private String categoryName;
}
