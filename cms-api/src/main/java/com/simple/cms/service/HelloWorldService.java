/* Copyright (C) MeaTech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package com.simple.cms.service;


import com.simple.cms.command.HelloWorldCommand;
import com.simple.cms.dto.HelloWorldDto;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface HelloWorldService {

    HelloWorldDto getHelloWorld(@Valid  HelloWorldCommand command);
}
