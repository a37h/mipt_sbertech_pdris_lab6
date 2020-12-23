package miptsbertechpdris.predict.service;

import java.util.List;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;

import miptsbertechpdris.currency.service.CurrencyService;
import miptsbertechpdris.weather.service.WeatherService;

@Service
public class PredictService {
  private final SimpleRegression regression;
  private final WeatherService weatherService;
  private final CurrencyService currencyService;
  private static final int N = 8;

  public PredictService(WeatherService weatherService, CurrencyService currencyService)  {
    this.weatherService = weatherService;
    this.currencyService = currencyService;
    this.regression = new SimpleRegression();
  }

  public double getPrediction() throws JsonProcessingException {
    this.fit();
    Double weatherData = weatherService.getForecast(/*city=*/null);
    return this.predict(weatherData);
  }

  private void fit() throws JsonProcessingException {
    List<Double> weatherData = weatherService.getWeather(N, /*city=*/null);
    List<Double> currencyData = currencyService.getCurrency(N);

    for (int i = 0; i < N; ++i) {
      regression.addData(weatherData.get(i), currencyData.get(i));
    }
  }

  private Double predict(Double weatherData) {
    return regression.predict(weatherData);
  }
}