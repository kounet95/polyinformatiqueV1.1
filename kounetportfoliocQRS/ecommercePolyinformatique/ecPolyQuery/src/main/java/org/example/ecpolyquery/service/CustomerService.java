package org.example.ecpolyquery.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.example.ecpolyquery.entity.Customer;
import org.example.ecpolyquery.repos.CustomerRepository;

import org.example.polyinformatiquecoreapi.dtoEcommerce.CustomerEcommerceDTO;
import org.example.polyinformatiquecoreapi.eventEcommerce.CustomerCreatedEvent;
import org.example.polyinformatiquecoreapi.eventEcommerce.CustomerDeletedEvent;
import org.example.polyinformatiquecoreapi.eventEcommerce.CustomerUpdatedEvent;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @EventHandler
    public void on(CustomerCreatedEvent event) {
        log.debug("Handling CustomerCreatedEvent: {}", event.getId());
        CustomerEcommerceDTO customerDTO = event.getAuthor();

        Customer customer = Customer.builder()
                .id(event.getId())
                .firstname(customerDTO.getFirstname())
                .lastname(customerDTO.getLastname())
                .email(customerDTO.getEmail())
                .phone(customerDTO.getPhone())
                .shippingAddress(customerDTO.getShippingAddress())
                .billingAddress(customerDTO.getBillingAddress())
                .build();

        customerRepository.save(customer);
        log.info("Customer created with ID: {}", customer.getId());
    }

    @EventHandler
    public void on(CustomerDeletedEvent event) {
        log.debug("Handling CustomerDeletedEvent: {}", event.getId());

        customerRepository.findById(event.getId())
                .ifPresent(customer -> {
                    customerRepository.delete(customer);
                    log.info("Customer deleted with ID: {}", event.getId());
                });
    }

    @EventHandler
    public void on(CustomerUpdatedEvent event) {
        log.debug("Handling CustomerUpdatedEvent: {}", event.getId());
        CustomerEcommerceDTO customerDTO = event.getCustomerDTO();

        customerRepository.findById(event.getId())
                .ifPresent(customer -> {
                    customer.setFirstname(customerDTO.getFirstname());
                    customer.setLastname(customerDTO.getLastname());
                    customer.setEmail(customerDTO.getEmail());
                    customer.setPhone(customerDTO.getPhone());
                    customer.setShippingAddress(customerDTO.getShippingAddress());
                    customer.setBillingAddress(customerDTO.getBillingAddress());

                    customerRepository.save(customer);
                    log.info("Customer updated with ID: {}", customer.getId());
                });
    }
}
