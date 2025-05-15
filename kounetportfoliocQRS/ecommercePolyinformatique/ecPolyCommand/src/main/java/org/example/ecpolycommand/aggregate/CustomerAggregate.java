package org.example.ecpolycommand.aggregate;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.example.polyinformatiquecoreapi.commandEcommerce.CreateCustomerCommand;
import org.example.polyinformatiquecoreapi.commandEcommerce.DeleteCustomerCommand;
import org.example.polyinformatiquecoreapi.eventEcommerce.CustomerCreatedEvent;
import org.example.polyinformatiquecoreapi.eventEcommerce.CustomerDeletedEvent;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * Customer Aggregate for handling customer-related commands
 */
@Aggregate
@Slf4j
@Getter
@Setter
public class CustomerAggregate {

    @AggregateIdentifier
    private String customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private String shippingAddress;
    private String billingAddress;

    public CustomerAggregate() {}


     @CommandHandler
     public CustomerAggregate(CreateCustomerCommand cmd) {
         apply(new CustomerCreatedEvent(cmd.getId(), cmd.getAuthor()));
     }


     @EventSourcingHandler
     public void on(CustomerCreatedEvent event) {
         this.customerId = event.getId();
         this.firstName = event.getAuthor().getFirstname();
         this.lastName = event.getAuthor().getLastname();
         this.email = event.getAuthor().getEmail();
         this.address = event.getAuthor().getShippingAddress();
         this.phone = event.getAuthor().getPhone();
         this.shippingAddress = event.getAuthor().getShippingAddress();
         this.billingAddress = event.getAuthor().getBillingAddress();
     }

     @CommandHandler
     public void handle(DeleteCustomerCommand command) {
         apply(new CustomerDeletedEvent(command.getId()));
     }

     @EventSourcingHandler
     public void on(CustomerDeletedEvent event) {

         this.firstName = "[deleted]";
         this.lastName = "[deleted]";
         this.email = null;
         this.address = null;
         this.phone = null;
         this.shippingAddress = null;
         this.billingAddress = null;
     }
}
