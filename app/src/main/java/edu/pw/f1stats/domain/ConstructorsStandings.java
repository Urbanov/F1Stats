package edu.pw.f1stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConstructorsStandings {

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class StandingsList {

        @JsonProperty("ConstructorStandings")
        private List<ConstructorsStandingsItem> constructorsStandings;
    }

    @JsonProperty("StandingsLists")
    private List<StandingsList> standingsLists;

    public List<ConstructorsStandingsItem> getStandings() {
        return standingsLists.isEmpty()
            ? Collections.emptyList()
            : standingsLists.get(0).constructorsStandings;
    }
}
