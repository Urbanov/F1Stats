package edu.pw.f1stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class QualifyingResult {

    @JsonProperty("position")
    private String position;

    @JsonProperty("Driver")
    private Driver driver;

    @JsonProperty("Q1")
    private String q1;

    @JsonProperty("Q2")
    private String q2;

    @JsonProperty("Q3")
    private String q3;
}
