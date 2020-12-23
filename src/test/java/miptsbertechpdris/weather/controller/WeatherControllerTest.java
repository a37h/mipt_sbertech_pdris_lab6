package miptsbertechpdris.weather.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
class WeatherControllerTest {
  @Autowired
  private WeatherController weatherController;

  @Test
  void getWeatherNullCityNotNullDays() throws Exception {
    MockMvc mvc = standaloneSetup(weatherController).build();
    mvc.perform(MockMvcRequestBuilders.get("/weather?days=5")).andExpect(status().isOk());
  }

  @Test
  void getWeatherNullCityNullDays() throws Exception {
    MockMvc mvc = standaloneSetup(weatherController).build();
    mvc.perform(MockMvcRequestBuilders.get("/weather")).andExpect(status().isOk());
  }

  @Test
  void getWeatherNotNullCityNotNullDays() throws Exception {
    MockMvc mvc = standaloneSetup(weatherController).build();
    mvc.perform(MockMvcRequestBuilders.get("/weather?days=5&city=London")).andExpect(status().isOk());
  }
  
  @Test
  void getWeatherNotNullCityNullDays() throws Exception {
    MockMvc mvc = standaloneSetup(weatherController).build();
    mvc.perform(MockMvcRequestBuilders.get("/weather?city=London")).andExpect(status().isOk());
  }
}