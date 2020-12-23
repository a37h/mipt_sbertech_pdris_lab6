package miptsbertechpdris.weather.controller;

import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;

import miptsbertechpdris.weather.service.WeatherService;

@RestController
public class WeatherController {
  private final WeatherService weatherService;

  public WeatherController(WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @GetMapping("/weather")
  public List<Double> getWeather(@RequestParam @Nullable String n, @RequestParam @Nullable String city)
      throws JsonProcessingException {
    int nInt = 1;
    if (n != null) {
      nInt = Integer.parseInt(n);
    }
    return weatherService.getWeather(nInt, city);
  }
}