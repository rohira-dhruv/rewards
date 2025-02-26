package com.learnings.diningrewards.rewards.internal;

import com.learnings.diningrewards.rewards.AccountContribution;
import com.learnings.diningrewards.rewards.Dining;
import com.learnings.diningrewards.rewards.RewardConfirmation;
import com.learnings.diningrewards.rewards.internal.reward.RewardRepository;

import java.util.Random;

/**
 * A dummy reward repository implementation.
 */
public class StubRewardRepository implements RewardRepository {

  public RewardConfirmation confirmReward(AccountContribution contribution, Dining dining) {
    return new RewardConfirmation(confirmationNumber(), contribution);
  }

  private String confirmationNumber() {
    return new Random().toString();
  }
}
