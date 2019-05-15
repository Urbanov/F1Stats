package edu.pw.f1stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Constructor {

    @JsonProperty("constructorId")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("nationality")
    private String nationality;
}
