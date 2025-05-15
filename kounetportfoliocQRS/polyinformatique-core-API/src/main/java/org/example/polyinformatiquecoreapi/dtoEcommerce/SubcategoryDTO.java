package org.example.polyinformatiquecoreapi.dtoEcommerce;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubcategoryDTO {
    private String id;

    @NotBlank(message = "Subcategory name cannot be blank")
    private String name;

    @NotBlank(message = "Category ID cannot be blank")
    private String categoryId;
}