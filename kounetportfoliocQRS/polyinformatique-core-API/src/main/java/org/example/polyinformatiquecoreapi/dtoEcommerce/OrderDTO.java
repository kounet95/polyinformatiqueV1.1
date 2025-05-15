package org.example.polyinformatiquecoreapi.dtoEcommerce;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDTO {
    private String id;

    @NotBlank(message = "Customer ID cannot be blank")
    private String customerId;

    @NotBlank(message = "CreatedAt cannot be blank")
    private String createdAt;

    @NotBlank(message = "Order status cannot be blank")
    private String orderStatus;

    @NotBlank(message = "Payment method cannot be blank")
    private String paymentMethod;

    @Min(value = 0, message = "Total must be positive")
    private double total;

    @NotBlank(message = "Barcode cannot be blank")
    private String barcode;
}