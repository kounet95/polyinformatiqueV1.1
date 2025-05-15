package org.example.commandeblog.mapper;

import org.example.commandeblog.aggreate.DomainAggregate;
import org.example.polyinformatiquecoreapi.dto.DomainDTO;

public interface DomainCommandMapper {
    DomainAggregate toAggregate(DomainDTO dto);
    DomainDTO toDTO(DomainAggregate aggregate);
}
