package miptsbertechpdris.currency.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
public class CurrencyControllerTest {
  @Autowired
  private CurrencyController currencyController;

  @Test
  void getCurrency() throws Exception {
      MockMvc mvc = standaloneSetup(currencyController).build();
      mvc.perform(MockMvcRequestBuilders.get("/currency?days=5")).andExpect(status().isOk());
  }
}