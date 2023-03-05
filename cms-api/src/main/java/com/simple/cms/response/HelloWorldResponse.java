/* Copyright (C) MeaTech - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package com.simple.cms.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "Hello world")
public class HelloWorldResponse {

    @JsonProperty("name")
    @ApiModelProperty(notes = "Hello world response", name="hello", value="hello mark")
    private String name;

}
