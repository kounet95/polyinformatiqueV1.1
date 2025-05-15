package org.example.commandeblog.mapper;

import org.example.commandeblog.aggreate.TagAggregate;
import org.example.polyinformatiquecoreapi.dto.TagDTO;

public interface TagCommandMapper {
    TagAggregate toAggregate(TagDTO dto);
    TagDTO toDTO(TagAggregate aggregate);
}
