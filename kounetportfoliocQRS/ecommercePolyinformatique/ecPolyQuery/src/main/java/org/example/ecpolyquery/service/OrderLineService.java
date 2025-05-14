package org.example.ecpolyquery.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.example.ecpolyquery.entity.OrderLine;
import org.example.ecpolyquery.entity.Orderecommerce;
import org.example.ecpolyquery.entity.ProductSize;
import org.example.ecpolyquery.repos.OrderLineRepository;
import org.example.ecpolyquery.repos.OrderecommerceRepository;
import org.example.ecpolyquery.repos.ProductSizeRepository;
import org.example.polyinformatiquecoreapi.dtoEcommerce.OrderLineDTO;
import org.example.polyinformatiquecoreapi.eventEcommerce.ProductAddedToOrderEvent;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderecommerceRepository orderecommerceRepository;
    private final ProductSizeRepository productSizeRepository;

    @EventHandler
    public void on(ProductAddedToOrderEvent event) {
        log.debug("Handling ProductAddedToOrderEvent: {}", event.getId());
        OrderLineDTO orderLineDTO = event.getOrderLineDTO();

        Orderecommerce order = orderecommerceRepository.findById(orderLineDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderLineDTO.getOrderId()));

        ProductSize productSize = productSizeRepository.findById(orderLineDTO.getProductSizeId())
                .orElseThrow(() -> new RuntimeException("ProductSize not found with id: " + orderLineDTO.getProductSizeId()));

        OrderLine orderLine = OrderLine.builder()
                .id(orderLineDTO.getId())
                .qty(orderLineDTO.getQty())
                .orderecommerce(order)
                .productSize(productSize)
                .build();

        orderLineRepository.save(orderLine);
        log.info("OrderLine created with ID: {}", orderLine.getId());
    }
}
