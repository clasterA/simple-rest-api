/* Copyright (C) MeaTech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package com.simple.cms.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ApiModel(description = "Hello world command")
public class HelloWorldCommand {

    @NotBlank
    @Length(min = 2, max = 300)
    @JsonProperty("name")
    @ApiModelProperty(notes = "Hello world name", name="name", required = true, value="test name")
    private String name;


}
