package com.wfm.demo;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.repository.FeatureState;

@RestController
public class MainController {

  private static final String[] SHORT_WEEKDAYS =
      DateFormatSymbols.getInstance().getShortWeekdays();

  private FeatureManager featureManager;
  public MainController(FeatureManager featureManager) {
    this.featureManager = featureManager;
  }

  @GetMapping
  @RequestMapping("/product")
  public ResponseEntity<String> getProduct() {
    String response = "";
    /*Calendar now = GregorianCalendar.getInstance();
    int currentWeekday = now.get(Calendar.DAY_OF_WEEK);
    String currentWeekdayName = SHORT_WEEKDAYS[currentWeekday];*/
    final FeatureState featureState = new FeatureState(MyFeatures.FEATURE_ONE);
    //featureState.setParameter("day", currentWeekdayName);
    if (featureManager.getActivationStrategies().get(0).isActive(
        featureState, null)) {
      response = response + MyFeatures.FEATURE_ONE.name() + " is enabled.";
    }
    if (featureManager.isActive(MyFeatures.FEATURE_TWO)) {
      response = response + MyFeatures.FEATURE_TWO.name() + " is enabled.";
    }
    return ResponseEntity.ok(response);
  }
}
