package com.github.gielr.model;

public enum WeatherCondition {
    GOOD("GOOD"),
    BAD("BAD"),
    AVERAGE("AVERAGE");

    private final String value;

    WeatherCondition(String weatherCondition) {
        this.value = weatherCondition;
    }

    public String getValue() {
        return value;
    }
}
