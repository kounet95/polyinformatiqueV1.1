package org.example.commandeblog.mapper;

import org.example.commandeblog.aggreate.ItemAggregate;
import org.example.polyinformatiquecoreapi.dto.ArticleDTO;

public interface ArticleCommandMapper {
    ItemAggregate toAggregate(ArticleDTO dto);
    ArticleDTO toDTO(ItemAggregate aggregate);
}