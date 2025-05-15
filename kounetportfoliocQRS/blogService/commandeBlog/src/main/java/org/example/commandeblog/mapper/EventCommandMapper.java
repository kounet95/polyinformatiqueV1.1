package org.example.commandeblog.mapper;

import org.example.commandeblog.aggreate.ItemAggregate;
import org.example.polyinformatiquecoreapi.dto.EventDTO;

public interface EventCommandMapper {
    ItemAggregate toAggregate(EventDTO dto);
    EventDTO toDTO(ItemAggregate aggregate);
}
