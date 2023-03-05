package com.simple.cms.service;

import com.simple.cms.command.NumberCommand;
import com.simple.cms.dto.HelloWorldDto;

public interface NumberService {

    HelloWorldDto getNumber(NumberCommand command);

}
