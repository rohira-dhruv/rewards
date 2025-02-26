package com.learnings.diningrewards.rewards.internal;

import com.learnings.diningrewards.common.money.Percentage;
import com.learnings.diningrewards.rewards.internal.restaurant.Restaurant;
import com.learnings.diningrewards.rewards.internal.restaurant.RestaurantRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * A dummy restaurant repository implementation. Has a single restaurant "Apple Bees" with an 8% benefit availability
 * percentage that's always available.
 * <p>
 * Stubs facilitate unit testing. An object needing a RestaurantRepository can work with this stub and not have to bring
 * in expensive and/or complex dependencies such as a Database. Simple unit tests can then verify object behavior by
 * considering the state of this stub.
 * </p>
 */
public class StubRestaurantRepository implements RestaurantRepository {

  private final Map<String, Restaurant> restaurantsByMerchantNumber = new HashMap<>();

  public StubRestaurantRepository() {
    Restaurant restaurant = new Restaurant("1234567890", "Apple Bees");
    restaurant.setBenefitPercentage(Percentage.valueOf("8%"));
    restaurantsByMerchantNumber.put(restaurant.getNumber(), restaurant);
  }

  public Restaurant findByMerchantNumber(String merchantNumber) {
    Restaurant restaurant = restaurantsByMerchantNumber.get(merchantNumber);
    if (restaurant == null) {
      throw new RuntimeException("no restaurant has been found for merchant number " + merchantNumber);
    }
    return restaurant;
  }
}
