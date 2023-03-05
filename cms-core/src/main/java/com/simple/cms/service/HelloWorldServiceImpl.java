package com.simple.cms.service;

import com.simple.cms.command.HelloWorldCommand;
import com.simple.cms.dto.HelloWorldDto;
import com.simple.cms.mapper.SimpleCmsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloWorldServiceImpl implements HelloWorldService{

    private final SimpleCmsMapper mapper;
    @Override
    public HelloWorldDto getHelloWorld(HelloWorldCommand command) {
        return mapper.map(command);
    }
}
