package miptsbertechpdris.currency.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {
  private final RestTemplate restTemplate;

  public CurrencyService(RestTemplateBuilder builder) {
    this.restTemplate = builder.build();
  }

  public List<Double> getCurrency(int n) {
    List<Double> currencyData = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) {
      ResponseEntity<String> responseEntity = restTemplate.getForEntity(getRequestURL(getDate(i)), String.class);
      currencyData.add(parseCurrencyValue(responseEntity));
    }
    return currencyData;
  }

  private String getDate(int i) {
    return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now().minusDays(i));
  }

  private String getRequestURL(String date) {
    return "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date;
  }

  private double parseCurrencyValue(ResponseEntity<String> responseEntity) {
    String body = responseEntity.getBody();
    String start = "США</Name><Value>";
    int startIndex = body.indexOf(start) + start.length();
    int endIndex = body.indexOf('<', startIndex);
    return Double.parseDouble(body.substring(startIndex, endIndex).replace(",", "."));
  }
}