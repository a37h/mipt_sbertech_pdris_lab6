package miptsbertechpdris.currency.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrencyServiceTest {
  @Autowired
  private CurrencyService currencyService;

  @Test
  void getCurrency() {
    List<Double> currency = currencyService.getCurrency(5);
    assertEquals(5, currency.size());
    currency.forEach(val -> assertTrue(70 < val && val < 90));
  }
}