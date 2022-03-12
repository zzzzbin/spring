
package com.example.demo.dto;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "capital",
    "mimtemp",
    "maxtemp",
    "currency"
})
@Generated("jsonschema2pojo")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Data {

    @JsonProperty("capital")
    private String capital;
    @JsonProperty("mimtemp")
    private Integer mimtemp;
    @JsonProperty("maxtemp")
    private Integer maxtemp;
    @JsonProperty("currency")
    private String currency;
}
