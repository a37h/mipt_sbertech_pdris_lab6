package miptsbertechpdris.predict.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@SpringBootTest
class PredictControllerTest {
  @Autowired
  private PredictController predictController;

  @Test
  void getPrediction() throws Exception {
    MockMvc mvc = standaloneSetup(predictController).build();
    mvc.perform(MockMvcRequestBuilders.get("/predict")).andExpect(status().isOk());
  }
}