package miptsbertechpdris.currency.controller;

import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import miptsbertechpdris.currency.service.CurrencyService;

@RestController
public class CurrencyController {
  private final CurrencyService currencyService;

  public CurrencyController(CurrencyService currencyService) {
    this.currencyService = currencyService;
  }

  @GetMapping("/currency")
  public List<Double> getCurrency(@RequestParam @Nullable String n) {
    int nInt = 1;
    if (n != null) {
      nInt = Integer.parseInt(n);
    }
    return currencyService.getCurrency(nInt);
  }
}