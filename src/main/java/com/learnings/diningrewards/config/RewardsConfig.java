package com.learnings.diningrewards.config;

import com.learnings.diningrewards.rewards.RewardNetwork;
import com.learnings.diningrewards.rewards.internal.RewardNetworkImpl;
import com.learnings.diningrewards.rewards.internal.account.AccountRepository;
import com.learnings.diningrewards.rewards.internal.account.JdbcAccountRepository;
import com.learnings.diningrewards.rewards.internal.restaurant.JdbcRestaurantRepository;
import com.learnings.diningrewards.rewards.internal.restaurant.RestaurantRepository;
import com.learnings.diningrewards.rewards.internal.reward.JdbcRewardRepository;
import com.learnings.diningrewards.rewards.internal.reward.RewardRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class RewardsConfig {

  private final DataSource dataSource;

  public RewardsConfig(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Bean
  RewardNetwork rewardNetwork() {
    return new RewardNetworkImpl(accountRepository(), restaurantRepository(), rewardRepository());
  }

  @Bean
  AccountRepository accountRepository() {
    JdbcAccountRepository repository = new JdbcAccountRepository();
    repository.setDataSource(dataSource);
    return repository;
  }

  @Bean
  RestaurantRepository restaurantRepository() {
    JdbcRestaurantRepository repository = new JdbcRestaurantRepository();
    repository.setDataSource(dataSource);
    return repository;
  }

  @Bean
  RewardRepository rewardRepository() {
    JdbcRewardRepository repository = new JdbcRewardRepository();
    repository.setDataSource(dataSource);
    return repository;
  }

}
