package org.example.commandeblog.mapper;

import org.example.commandeblog.aggreate.AuthorAggregate;
import org.example.polyinformatiquecoreapi.dto.CustomerBlogDTO;

public interface AuthorCommandMapper {
    AuthorAggregate toAggregate(CustomerBlogDTO dto);
    CustomerBlogDTO toDTO(AuthorAggregate aggregate);
}
