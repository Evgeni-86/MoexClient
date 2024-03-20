package org.moexclient.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class ResponseModel {
    @JsonProperty("Date") String date;
    @JsonProperty("JuridicalLong") String juridicalLong;
    @JsonProperty("JuridicalShort") String juridicalShort;
    @JsonProperty("PhysicalLong") String physicalLong;
    @JsonProperty("PhysicalShort") String physicalShort;
    @JsonProperty("Summary") String summary;

    public String getDate() {
        return date;
    }

    public int getJuridicalLong() {
        return Integer.parseInt(juridicalLong.replaceAll("\\D+", ""));
    }

    public int getJuridicalShort() {
        return Integer.parseInt(juridicalShort.replaceAll("\\D+", ""));
    }

    public int getPhysicalLong() {
        return Integer.parseInt(physicalLong.replaceAll("\\D+", ""));
    }

    public int getPhysicalShort() {
        return Integer.parseInt(physicalShort.replaceAll("\\D+", ""));
    }

    public int getSummary() {
        return Integer.parseInt(summary.replaceAll("\\D+", ""));
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "date='" + date + '\'' +
                ", juridicalLong='" + juridicalLong + '\'' +
                ", juridicalShort='" + juridicalShort + '\'' +
                ", physicalLong='" + physicalLong + '\'' +
                ", physicalShort='" + physicalShort + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
