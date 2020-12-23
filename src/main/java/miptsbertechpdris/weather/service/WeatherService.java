package miptsbertechpdris.weather.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {
  private static final String API_KEY = "9c06e4dc7ee04e71bb343251202312";
  private static final String DEFAULT_CITY = "Moscow";
  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  public WeatherService(RestTemplateBuilder builder, ObjectMapper objectMapper) {
    this.restTemplate = builder.build();
    this.objectMapper = objectMapper;
  }

  public List<Double> getWeather(int n, String city) throws JsonProcessingException {
    if (city == null) {
      city = DEFAULT_CITY;
    }
    List<Double> weatherData = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      ResponseEntity<String> responseEntity = restTemplate.getForEntity(getWeatherRequestURL(getDate(i), city), String.class);
      weatherData.add(parseWeatherValue(responseEntity));
    }
    return weatherData;
  }

  public Double getForecast(String city) throws JsonProcessingException {
    if (city == null) {
      city = DEFAULT_CITY;
    }
    ResponseEntity<String> responseEntity = restTemplate.getForEntity(getForecastRequestURL(city), String.class);
    return parseWeatherValue(responseEntity);
  }

  private String getDate(int i) {
    return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now().minusDays(i));
  }

  private String getWeatherRequestURL(String date, String city) {
    return "http://api.weatherapi.com/v1/history.json?key=" + API_KEY + "&q=" + city + "&dt=" + date;
  }

  private String getForecastRequestURL(String city) {
    return "http://api.weatherapi.com/v1/forecast.json" + "?key=" + API_KEY + "&q=" + city + "&days=1";
  }

  private Double parseWeatherValue(ResponseEntity<String> responseEntity) throws JsonProcessingException {
    JsonNode node = objectMapper.readTree(responseEntity.getBody());
    if (node.get("forecast") != null) {
      node = node.get("forecast").get("forecastday").get(0).get("day");
    }
    return node.get("avgtemp_c").asDouble();
  }
}