package org.example.commandeblog.mapper;

import org.example.commandeblog.aggreate.MediaAggregate;
import org.example.polyinformatiquecoreapi.dto.MediaDTO;

public interface MediaCommandMapper {
    MediaAggregate toAggregate(MediaDTO dto);
    MediaDTO toDTO(MediaAggregate aggregate);
}