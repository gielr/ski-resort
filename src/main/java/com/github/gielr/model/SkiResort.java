package com.github.gielr.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class SkiResort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private BigDecimal price;

    @OneToMany(mappedBy = "skiResort")
    private Set<SkiResortWeather> skiResortWeathers;

    @Enumerated(EnumType.STRING)
    private WeatherCondition weatherCondition;

    public SkiResort() {
    }

    public SkiResort(String city, BigDecimal price, Set<SkiResortWeather> skiResortWeathers, WeatherCondition weatherCondition) {
        this.city = city;
        this.price = price;
        this.skiResortWeathers = skiResortWeathers;
        this.weatherCondition = weatherCondition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public Set<SkiResortWeather> getSkiResortWeathers() {
        return skiResortWeathers;
    }

    public void setSkiResortWeathers(Set<SkiResortWeather> skiResortWeathers) {
        this.skiResortWeathers = skiResortWeathers;
    }
}
