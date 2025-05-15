package org.example.polyinformatiquecoreapi.dtoEcommerce;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDTO {
    private String id;

    @NotBlank(message = "Product name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Min(value = 0, message = "Price must be positive")
    private double price;

    @NotBlank(message = "CreatedAt cannot be blank")
    private String createdAt;

    private String closedAt;

    @NotBlank(message = "Subcategory ID cannot be blank")
    private String subcategoryId;

    @NotBlank(message = "Social group ID cannot be blank")
    private String socialGroupId;

    @NotBlank(message = "Image URL cannot be blank")
    private String imageUrl;

    @NotNull(message = "isActive cannot be null")
    private Boolean isActive;
}