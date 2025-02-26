package com.learnings.diningrewards.rewards;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

@Configuration
public class TestInfrastructureConfig {

  /**
   * Creates an in-memory "rewards" database populated
   * with test data for fast testing
   */
  @Bean
  public DataSource dataSource() {
    return (new EmbeddedDatabaseBuilder()) //
      .addScript("classpath:rewards/testdb/schema.sql") //
      .addScript("classpath:rewards/testdb/data.sql") //
      .build();
  }
}
