package com.learnings.diningrewards.rewards.internal;

import com.learnings.diningrewards.common.money.Percentage;
import com.learnings.diningrewards.rewards.internal.account.Account;
import com.learnings.diningrewards.rewards.internal.account.AccountRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * A dummy account repository implementation. Has a single Account "Keith and Keri Donald" with two beneficiaries
 * "Annabelle" (50% allocation) and "Corgan" (50% allocation) associated with credit card "1234123412341234".
 * <p>
 * Stubs facilitate unit testing. An object needing an AccountRepository can work with this stub and not have to bring
 * in expensive and/or complex dependencies such as a Database. Simple unit tests can then verify object behavior by
 * considering the state of this stub.
 * </p>
 */
public class StubAccountRepository implements AccountRepository {

  private final Map<String, Account> accountsByCreditCard = new HashMap<>();

  public StubAccountRepository() {
    Account account = new Account("123456789", "Keith and Keri Donald");
    account.addBeneficiary("Annabelle", Percentage.valueOf("50%"));
    account.addBeneficiary("Corgan", Percentage.valueOf("50%"));
    accountsByCreditCard.put("1234123412341234", account);
  }

  public Account findByCreditCard(String creditCardNumber) {
    Account account = accountsByCreditCard.get(creditCardNumber);
    if (account == null) {
      throw new RuntimeException("no account has been found for credit card number " + creditCardNumber);
    }
    return account;
  }

  public void updateBeneficiaries(Account account) {
    // nothing to do, everything is in memory
  }
}