package org.example.commandeblog.mapper;

import org.example.commandeblog.aggreate.ItemAggregate;
import org.example.polyinformatiquecoreapi.dto.NewsDTO;

public interface NewsCommandMapper {
    ItemAggregate toAggregate(NewsDTO dto);
    NewsDTO toDTO(ItemAggregate aggregate);
}
