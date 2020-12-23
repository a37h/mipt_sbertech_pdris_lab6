package miptsbertechpdris.predict.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import miptsbertechpdris.weather.service.WeatherService;
import miptsbertechpdris.currency.service.CurrencyService;

@SpringBootTest
class PredictServiceTest {
  @Autowired
  private WeatherService weatherService;
  @Autowired
  private CurrencyService currencyService;

  @Test
  void predict() throws Exception {
    PredictService predictService = new PredictService(weatherService, currencyService);
    double predicted = predictService.getPrediction();
    assertTrue(70 < predicted && predicted < 80);
  }
}