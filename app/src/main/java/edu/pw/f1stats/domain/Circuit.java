package edu.pw.f1stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Circuit implements Serializable {

    @JsonProperty("circuitName")
    private String name;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Location {

        @JsonProperty("country")
        private String country;
    }

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    @JsonProperty("Location")
    private Location location;

    public String getCountry() {
        return location.country;
    }
}
