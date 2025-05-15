package org.example.polyinformatiquecoreapi.dtoEcommerce;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ShippingDTO {
    private String id;

    @NotBlank(message = "Order ID cannot be blank")
    private String orderId;

    @NotBlank(message = "Delivery status cannot be blank")
    private String deliveryStatus;

    @NotBlank(message = "Estimated delivery date cannot be blank")
    private String estimatedDeliveryDate;

    @NotBlank(message = "Shipping date cannot be blank")
    private String shippingDate;

    @NotBlank(message = "Shipping address cannot be blank")
    private String shippingAddress;
}