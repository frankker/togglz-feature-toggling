package com.wfm.demo;

import java.io.File;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.Feature;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.file.FileBasedStateRepository;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.core.user.UserProvider;
import org.togglz.servlet.user.ServletUserProvider;

@Configuration
public class DemoConfiguration implements TogglzConfig {

  public Class<? extends Feature> getFeatureClass() {
    return MyFeatures.class;
  }

  public StateRepository getStateRepository() {
    return new FileBasedStateRepository(new File("/tmp/features.properties"));
  }

  public UserProvider getUserProvider() {
    return new ServletUserProvider("admin");
  }

  @Bean
  public FeatureProvider featureProvider() {
    return new EnumBasedFeatureProvider(MyFeatures.class);
  }
}
