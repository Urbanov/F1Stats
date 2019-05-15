package edu.pw.f1stats.domain;

import android.text.TextUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Driver {

    @JsonProperty("driverId")
    private String id;

    @JsonProperty("givenName")
    private String firstName;

    @JsonProperty("familyName")
    private String lastName;

    @JsonProperty("nationality")
    private String nationality;

    public String getFullName() {
        return TextUtils.join(" ", Arrays.asList(firstName, lastName));
    }
}
