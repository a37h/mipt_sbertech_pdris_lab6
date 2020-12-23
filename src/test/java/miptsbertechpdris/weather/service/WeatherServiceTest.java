package miptsbertechpdris.weather.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherServiceTest {
  @Autowired
  private WeatherService weatherService;

  @Test
  void getWeatherDefault() throws Exception {
    List<Double> weather = weatherService.getWeather(5, null);
    assertEquals(5, weather.size());
  }

  @Test
  void getWeatherCustom() throws Exception {
    List<Double> weather = weatherService.getWeather(5, "London");
    assertEquals(5, weather.size());
  }

  @Test
  void getForecast() throws Exception {
    Double forecast = weatherService.getForecast(null);
    assertTrue(-40 < forecast && forecast < 40);
  }
}