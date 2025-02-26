package com.learnings.diningrewards.rewards.internal.reward;

import com.learnings.diningrewards.rewards.AccountContribution;
import com.learnings.diningrewards.rewards.Dining;
import com.learnings.diningrewards.rewards.RewardConfirmation;

/**
 * Handles creating records of reward transactions to track contributions made to accounts for dining at restaurants.
 */
public interface RewardRepository {

  /**
   * Create a record of a reward that will track a contribution made to an account for dining.
   * @param contribution the account contribution that was made
   * @param dining the dining event that resulted in the account contribution
   * @return a reward confirmation object that can be used for reporting and to look up the reward details at a later
   * date
   */
  RewardConfirmation confirmReward(AccountContribution contribution, Dining dining);
}