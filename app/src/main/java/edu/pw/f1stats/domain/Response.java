package edu.pw.f1stats.domain;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    @Data
    @JsonIgnoreProperties({"xmlns", "series", "url", "limit", "offset", "total"})
    private static class MRData<U> {
        private U data;

        @JsonAnySetter
        public void setData(String name, U data) {
            this.data = data;
        }
    }

    @JsonProperty("MRData")
    private MRData<T> data;

    public T getData() {
        return data.getData();
    }
}
