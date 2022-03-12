
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
    "country",
    "data"
})
@Generated("jsonschema2pojo")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Country {

    @JsonProperty("country")
    private String country;
    @JsonProperty("data")
    private Data data;
}
