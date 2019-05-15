package edu.pw.f1stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DriversStandings {

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class StandingsList {

        @JsonProperty("DriverStandings")
        private List<DriversStandingsItem> driversStandings;
    }

    @JsonProperty("StandingsLists")
    @Setter
    private List<StandingsList> standingsLists;

    public List<DriversStandingsItem> getStandings() {
        return standingsLists.isEmpty()
            ? Collections.emptyList()
            : standingsLists.get(0).driversStandings;
    }
}
