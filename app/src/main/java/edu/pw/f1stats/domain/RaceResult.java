package edu.pw.f1stats.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Optional;

import lombok.Data;
import lombok.Setter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RaceResult implements Serializable {

    @JsonProperty("positionText")
    private String position;

    @JsonProperty("points")
    private String points;

    @JsonProperty("Driver")
    private Driver driver;

    @JsonProperty("Constructor")
    private Constructor constructor;

    @JsonProperty("grid")
    private String grid;

    @JsonProperty("laps")
    private String laps;

    @JsonProperty("status")
    private String status;

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Time {

        @JsonProperty("time")
        private String time;
    }

    @JsonProperty("Time")
    private Time time;

    public String getTime() {
        return Optional.ofNullable(time)
                .map(t -> time.time)
                .orElse(null);
    }
}
