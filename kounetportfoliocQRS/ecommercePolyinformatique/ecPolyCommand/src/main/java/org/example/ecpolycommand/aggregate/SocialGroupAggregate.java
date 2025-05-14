package org.example.ecpolycommand.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.example.polyinformatiquecoreapi.commandEcommerce.CreateSocialGroupCommand;
import org.example.polyinformatiquecoreapi.dtoEcommerce.SocialGroupDTO;
import org.example.polyinformatiquecoreapi.eventEcommerce.SocialGroupCreatedEvent;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * SocialGroup Aggregate for handling social group-related commands
 */
@Aggregate
public class SocialGroupAggregate {

    @AggregateIdentifier
    private String socialGroupId;
    private String name;
    private String description;
    private String type;

    public SocialGroupAggregate() {}


     @CommandHandler
     public SocialGroupAggregate(CreateSocialGroupCommand cmd) {
         apply(new SocialGroupCreatedEvent(cmd.getId(), cmd.getSocialGroupDTO()));
     }


     @EventSourcingHandler
     public void on(SocialGroupCreatedEvent event) {
         this.socialGroupId = event.getId();
         this.name = event.getSocialGroupDTO().getName();
         this.description = event.getSocialGroupDTO().getRegion();
         this.type = event.getSocialGroupDTO().getCountry();
     }
}
