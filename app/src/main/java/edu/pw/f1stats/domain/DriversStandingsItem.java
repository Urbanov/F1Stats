package edu.pw.f1stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DriversStandingsItem {

    @JsonProperty("positionText")
    private String position;

    @JsonProperty("points")
    private String points;

    @JsonProperty("Driver")
    private Driver driver;

    @JsonProperty("Constructors")
    private List<Constructor> constructors;

    public Constructor getConstructor() {
        return constructors.get(0);
    }
}
