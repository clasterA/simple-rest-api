package com.simple.cms.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(description = "number response")
public class NumberResponse {

    @JsonProperty("number")
    private String number;

}
