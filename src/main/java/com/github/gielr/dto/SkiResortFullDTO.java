package com.github.gielr.dto;

import com.github.gielr.model.SkiResort;
import com.github.gielr.model.SkiResortWeather;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;

public class SkiResortFullDTO {
    private String city;
    private BigDecimal price;
    private BigDecimal temperature;
    private Integer humidity;
    private Integer pressure;
    private LocalDateTime createdAt;

    public SkiResortFullDTO() {
    }

    public SkiResortFullDTO(SkiResort skiResort) {
        this.city = skiResort.getCity();
        this.price = skiResort.getPrice();
        SkiResortWeather latestSkiResortWeather = skiResort.getSkiResortWeathers()
                .stream()
                .max(Comparator.comparing(SkiResortWeather::getCreatedAt)).orElseThrow(RuntimeException::new);
        this.temperature = latestSkiResortWeather.getTemperature();
        this.pressure = latestSkiResortWeather.getPressure();
        this.humidity = latestSkiResortWeather.getHumidity();
        this.createdAt = latestSkiResortWeather.getCreatedAt().toLocalDateTime();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }
}
