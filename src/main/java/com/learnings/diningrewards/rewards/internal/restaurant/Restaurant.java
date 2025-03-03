package com.learnings.diningrewards.rewards.internal.restaurant;

import com.learnings.diningrewards.common.money.MonetaryAmount;
import com.learnings.diningrewards.common.money.Percentage;
import com.learnings.diningrewards.common.repository.Entity;
import com.learnings.diningrewards.rewards.Dining;
import com.learnings.diningrewards.rewards.internal.account.Account;

/**
 * A restaurant establishment in the network. Like AppleBee's.
 * <p>
 * Restaurants calculate how much benefit may be awarded to an account for dining based on a benefit percentage.
 * </p>
 */
public class Restaurant extends Entity {

  private String number;

  private String name;

  private Percentage benefitPercentage;

  @SuppressWarnings("unused")
  private Restaurant() {
  }

  /**
   * Creates a new restaurant.
   * @param number the restaurant's merchant number
   * @param name the name of the restaurant
   */
  public Restaurant(String number, String name) {
    this.number = number;
    this.name = name;
  }

  /**
   * Sets the percentage benefit to be awarded for eligible dining transactions.
   * @param benefitPercentage the benefit percentage
   */
  public void setBenefitPercentage(Percentage benefitPercentage) {
    this.benefitPercentage = benefitPercentage;
  }

  /**
   * Returns the name of this restaurant.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the merchant number of this restaurant.
   */
  public String getNumber() {
    return number;
  }

  /**
   * Returns this restaurant's benefit percentage.
   */
  public Percentage getBenefitPercentage() {
    return benefitPercentage;
  }

  /**
   * Calculate the benefit eligible to this account for dining at this restaurant.
   * @param account the account that dined at this restaurant
   * @param dining a dining event that occurred
   * @return the benefit amount eligible for reward
   */
  public MonetaryAmount calculateBenefitFor(Account account, Dining dining) {
    return dining.getAmount().multiplyBy(benefitPercentage);
  }

  public String toString() {
    return "Number = '" + number + "', name = '" + name + "', benefitPercentage = " + benefitPercentage;
  }
}
