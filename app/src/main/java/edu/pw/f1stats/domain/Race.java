package edu.pw.f1stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Race implements Serializable {

    @JsonProperty("season")
    private Integer season;

    @JsonProperty("round")
    private Integer round;

    @JsonProperty("raceName")
    private String name;

    @JsonProperty("Circuit")
    private Circuit circuit;

    @JsonProperty("Results")
    private List<RaceResult> raceResults;

    @JsonProperty("QualifyingResults")
    private List<QualifyingResult> qualifyingResults;

    @JsonProperty("url")
    private String url;

    @JsonProperty("date")
    private String date;

    @JsonProperty("time")
    private String time;
}
