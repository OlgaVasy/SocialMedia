package com.example.mapper;

import org.mapstruct.Mapper;

import com.example.dto.HashtagDto;
import com.example.entities.Hashtag;

@Mapper(componentModel = "spring")
public interface HashtagMapper {

	Hashtag fromDto(HashtagDto dto);

	HashtagDto toDto(Hashtag hashtag);

}
