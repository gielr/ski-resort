package com.github.gielr;

import com.github.gielr.model.SkiResort;
import com.github.gielr.repository.SkiResortRepository;
import com.github.gielr.service.SkiResortWeatherService;
import com.github.gielr.service.implementation.SkiResortWeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
@EntityScan(basePackageClasses = {Application.class, Jsr310JpaConverters.class})
@EnableScheduling
public class Application {
    private SkiResortRepository skiResortRepository;
    private SkiResortWeatherService skiResortWeatherService;

    @Autowired
    public Application(SkiResortRepository skiResortRepository, SkiResortWeatherService skiResortWeatherService) {
        this.skiResortRepository = skiResortRepository;
        this.skiResortWeatherService = skiResortWeatherService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("brak")
                .directModelSubstitute(LocalDateTime.class, String.class)
                .directModelSubstitute(LocalDate.class, String.class)
                .directModelSubstitute(LocalTime.class, String.class)
                .directModelSubstitute(ZonedDateTime.class, String.class)
                .select()
                .paths(regex("/api/.*"))
                .build();
    }

    //@Scheduled(cron = "0 4 * * * ?")
    @Scheduled(fixedRate = 300000L)
    public void scheduled(){
        Set<SkiResort> allSkiResort = new HashSet<>(skiResortRepository.findAll());
        allSkiResort.forEach(s->skiResortWeatherService.createSkiResortWeather(s.getCity()));
        System.out.println("Zaktualizowalem pogode");
    }
}
