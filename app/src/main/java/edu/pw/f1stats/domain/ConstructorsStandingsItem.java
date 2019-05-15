package edu.pw.f1stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstructorsStandingsItem {

    @JsonProperty("positionText")
    private String position;

    @JsonProperty("points")
    private String points;

    @JsonProperty("Constructor")
    private Constructor constructor;
}
