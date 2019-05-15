package edu.pw.f1stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Season implements Serializable {

    @JsonProperty("season")
    Integer season;
}
