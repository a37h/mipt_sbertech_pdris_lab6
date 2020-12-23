package miptsbertechpdris.predict.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;

import miptsbertechpdris.predict.service.PredictService;

@RestController
public class PredictController {
  private final PredictService predictService;

  public PredictController(PredictService predictService) {
    this.predictService = predictService;
  }

  @GetMapping("/predict")
  public Double getPrediction() throws JsonProcessingException {
    return predictService.getPrediction();
  }
}