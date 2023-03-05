package com.simple.cms.mapper;

import com.simple.cms.command.HelloWorldCommand;
import com.simple.cms.dto.HelloWorldDto;
import com.simple.cms.response.HelloWorldResponse;
import com.simple.cms.response.NumberResponse;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        componentModel = "spring")
public interface SimpleCmsMapper {

    HelloWorldResponse map(HelloWorldDto dto);

    HelloWorldDto map(HelloWorldCommand dto);

    NumberResponse map(String number);

}
