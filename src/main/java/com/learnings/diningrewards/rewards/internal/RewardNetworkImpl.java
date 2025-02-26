package com.learnings.diningrewards.rewards.internal;

import com.learnings.diningrewards.common.money.MonetaryAmount;
import com.learnings.diningrewards.rewards.AccountContribution;
import com.learnings.diningrewards.rewards.Dining;
import com.learnings.diningrewards.rewards.RewardConfirmation;
import com.learnings.diningrewards.rewards.RewardNetwork;
import com.learnings.diningrewards.rewards.internal.account.Account;
import com.learnings.diningrewards.rewards.internal.account.AccountRepository;
import com.learnings.diningrewards.rewards.internal.restaurant.Restaurant;
import com.learnings.diningrewards.rewards.internal.restaurant.RestaurantRepository;
import com.learnings.diningrewards.rewards.internal.reward.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Rewards an Account for Dining at a Restaurant.
 * The sole Reward Network implementation. This object is an application-layer service responsible for coordinating with
 * the domain-layer to carry out the process of rewarding benefits to accounts for dining.
 * Said in other words, this class implements the "reward account for dining" use case.
 */
@Service("rewardNetwork")
public class RewardNetworkImpl implements RewardNetwork {

  private final AccountRepository accountRepository;

  private final RestaurantRepository restaurantRepository;

  private final RewardRepository rewardRepository;

  /**
   * Creates a new reward network.
   * @param accountRepository the repository for loading accounts to reward
   * @param restaurantRepository the repository for loading restaurants that determine how much to reward
   * @param rewardRepository the repository for recording a record of successful reward transactions
   */
  @Autowired
  public RewardNetworkImpl(AccountRepository accountRepository, RestaurantRepository restaurantRepository,
                           RewardRepository rewardRepository) {
    this.accountRepository = accountRepository;
    this.restaurantRepository = restaurantRepository;
    this.rewardRepository = rewardRepository;
  }

  public RewardConfirmation rewardAccountFor(Dining dining) {
    Account account = accountRepository.findByCreditCard(dining.getCreditCardNumber());
    Restaurant restaurant = restaurantRepository.findByMerchantNumber(dining.getMerchantNumber());
    MonetaryAmount amount = restaurant.calculateBenefitFor(account, dining);
    AccountContribution contribution = account.makeContribution(amount);
    accountRepository.updateBeneficiaries(account);
    return rewardRepository.confirmReward(contribution, dining);
  }
}