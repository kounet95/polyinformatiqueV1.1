package org.example.commandeblog.mapper;

import org.example.commandeblog.aggreate.CommentAggregate;
import org.example.polyinformatiquecoreapi.dto.CommentDTO;

public interface CommentCommandMapper {
    CommentAggregate toAggregate(CommentDTO dto);
    CommentDTO toDTO(CommentAggregate aggregate);
}