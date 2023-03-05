package com.simple.cms.controller;

import com.simple.cms.command.HelloWorldCommand;
import com.simple.cms.mapper.SimpleCmsMapper;
import com.simple.cms.response.HelloWorldResponse;
import com.simple.cms.response.NumberResponse;
import com.simple.cms.service.HelloWorldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Simple cms")
@ComponentScan("com.simple.cms")
@RestController
@RequestMapping(value = "/hello-world", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class SimpleCmsController {

    private final SimpleCmsMapper mapper;
    private final HelloWorldService service;

    @ApiOperation("Get hello world")
    @PostMapping(value = "/")
    public ResponseEntity<HelloWorldResponse> postHelloWorld() {

        return ResponseEntity.status(HttpStatus.OK).body(
                mapper.map(
                        service.getHelloWorld( HelloWorldCommand
                                .builder()
                                .name("Danila")
                                .build()
                        )
                )
        );

    }

    @GetMapping(value = "/{number}")
    public ResponseEntity<NumberResponse> getNumber(@PathVariable String number) {

       return ResponseEntity.status(HttpStatus.OK).body(
            mapper.map(number)
       );

    }

}
