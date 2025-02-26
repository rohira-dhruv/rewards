package com.learnings.diningrewards.rewards.internal.restaurant;

import javax.sql.DataSource;

import com.learnings.diningrewards.common.money.Percentage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the JDBC restaurant repository with a test data source to verify data access and relational-to-object mapping
 * behavior works as expected.
 */
class JdbcRestaurantRepositoryTests {

  private JdbcRestaurantRepository repository;

  @BeforeEach
  public void setUp() {
    repository = new JdbcRestaurantRepository();
    repository.setDataSource(createTestDataSource());
    repository.populateRestaurantCache();
  }

  @AfterEach
  public void tearDown(){
    repository.clearRestaurantCache();
  }

  @Test
  void findRestaurantByMerchantNumber() {
    Restaurant restaurant = repository.findByMerchantNumber("1234567890");
    assertNotNull(restaurant, "restaurant is null - repository cache not likely initialized");
    assertEquals("1234567890", restaurant.getNumber(), "number is wrong");
    assertEquals("AppleBees", restaurant.getName(), "name is wrong");
    assertEquals(Percentage.valueOf("8%"), restaurant.getBenefitPercentage(), "benefitPercentage is wrong");
  }

  @Test
  void findRestaurantByBogusMerchantNumber() {
    assertThrows(EmptyResultDataAccessException.class, () -> repository.findByMerchantNumber("bogus"));
  }

  @Test
  void restaurantCacheClearedAfterDestroy() {
    tearDown();
    assertThrows(EmptyResultDataAccessException.class, () -> repository.findByMerchantNumber("1234567890"));
  }

  private DataSource createTestDataSource() {
    return new EmbeddedDatabaseBuilder()
      .setName("rewards")
      .addScript("/rewards/testdb/schema.sql")
      .addScript("/rewards/testdb/data.sql")
      .build();
  }
}

