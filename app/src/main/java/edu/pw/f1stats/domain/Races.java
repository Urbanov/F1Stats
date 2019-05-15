package edu.pw.f1stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Races {

    @JsonProperty("Races")
    private List<Race> races;

    public Race getSingleRace() {
        return races.stream()
            .findFirst()
            .orElse(null);
    }
}
