package com.github.gielr.service.implementation;

import com.github.gielr.model.SkiResort;
import com.github.gielr.model.SkiResortWeather;
import com.github.gielr.repository.SkiResortRepository;
import com.github.gielr.repository.SkiResortWeatherRepository;
import com.github.gielr.service.SkiResortWeatherService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;

@Service
public class SkiResortWeatherServiceImpl implements SkiResortWeatherService {
    private SkiResortWeatherRepository skiResortWeatherRepository;
    private SkiResortRepository skiResortRepository;

    @Autowired
    public SkiResortWeatherServiceImpl(SkiResortWeatherRepository skiResortWeatherRepository, SkiResortRepository skiResortRepository) {
        this.skiResortWeatherRepository = skiResortWeatherRepository;
        this.skiResortRepository = skiResortRepository;
    }

    @Override
    public SkiResortWeather createSkiResortWeather(String city){
        SkiResortWeather skiResortWeather = new SkiResortWeather();

        String link = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=e0d1bd5c6709a5d3cebb23193dcd1d92";

        String res = jsonGetRequest(link);
        JSONObject jsonObject = new JSONObject(res);
        jsonObject = jsonObject.getJSONObject("main");

        //Temperature
        Number temperature = jsonObject.getNumber("temp");
        double convertForTemperature = temperature.doubleValue();
        BigDecimal bigDecimalTemperature = BigDecimal.valueOf(convertForTemperature).subtract(BigDecimal.valueOf(273.15));
        skiResortWeather.setTemperature(bigDecimalTemperature);

        //Pressure
        Number pressure = jsonObject.getNumber("pressure");
        Integer convertForPressure = pressure.intValue();
        skiResortWeather.setPressure(convertForPressure);

        //Humidity
        Number humidity = jsonObject.getNumber("humidity");
        Integer convertForHumidity = humidity.intValue();
        skiResortWeather.setHumidity(convertForHumidity);

        skiResortWeather.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        SkiResort skiResort = skiResortRepository.findOneByCity(city);
        skiResortWeather.setSkiResort(skiResort);
        return skiResortWeatherRepository.save(skiResortWeather);
    }

    private static String jsonGetRequest(String link) {
        String json = null;
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = streamToString(inStream); // input stream to string
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    private static String streamToString(InputStream inputStream) {
        return new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
    }
}
