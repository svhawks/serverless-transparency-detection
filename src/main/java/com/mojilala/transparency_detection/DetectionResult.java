package com.mojilala.transparency_detection;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mrsfy on 15-Feb-17.
 */
public class DetectionResult {

    private double transparencyRate;

    public DetectionResult(double transparencyRate) {
        this.transparencyRate = transparencyRate;
    }

    @JsonProperty("transparency_rate")
    public double getTransparencyRate() {
        return transparencyRate;
    }

    public void setTransparencyRate(double transparencyRate) {
        this.transparencyRate = transparencyRate;
    }
}
