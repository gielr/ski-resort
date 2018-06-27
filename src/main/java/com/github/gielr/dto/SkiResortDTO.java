package com.github.gielr.dto;

import com.github.gielr.model.SkiResort;

import java.math.BigDecimal;

public class SkiResortDTO {
    private String city;
    private BigDecimal price;

    public SkiResortDTO() {
    }

    public SkiResortDTO(SkiResort skiResort) {
        this.city = skiResort.getCity();
        this.price = skiResort.getPrice();
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
}
