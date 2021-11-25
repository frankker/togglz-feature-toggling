package com.wfm.demo;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.togglz.core.activation.Parameter;
import org.togglz.core.activation.ParameterBuilder;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

public class WeekdayActivationStrategy implements ActivationStrategy {

  private static final String[] SHORT_WEEKDAYS =
      DateFormatSymbols.getInstance().getShortWeekdays();

  @Override
  public String getId() {
    return "weekday";
  }

  @Override
  public String getName() {
    return "Weekday strategy";
  }

  @Override
  public Parameter[] getParameters() {
    return new Parameter[] {
        ParameterBuilder.create("day").label("Day of week")
    };
  }

  @Override
  public boolean isActive(FeatureState featureState, FeatureUser user) {
    // the weekday name configured for the strategy
    // String selectedWeekday = featureState.getParameter("day");

    // get the weekday name for today
    Calendar now = GregorianCalendar.getInstance();
    // int currentWeekday = now.get(Calendar.DAY_OF_WEEK);
    int currentWeekday = now.get(Calendar.DAY_OF_WEEK);
    // String currentWeekdayName = SHORT_WEEKDAYS[currentWeekday];
    // the feature is active if the weekday matches
    // return selectedWeekday.equals(currentWeekdayName);
    return (1 < currentWeekday) && (currentWeekday < 6);
  }

}
