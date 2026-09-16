package com.test;

public class BankTX {
    private long[] balance;

    public BankTX(long[] balance) {
        this.balance = balance;
    }

    public boolean transfer(int account1, int account2, long money) {
        if (validAccountNumber(account1) && validAccountNumber(account2)
                && balance[account1 - 1] >= money) {
            balance[account1 - 1] -= money;
            balance[account2 - 1] += money;
            return true;
        }
        return false;
    }

    public boolean deposit(int account, long money) {
        if (validAccountNumber(account)) {
            balance[account - 1] += money;
            return true;
        }
        return false;
    }

    public boolean withdraw(int account, long money) {
        if (validAccountNumber(account) && balance[account - 1] >= money) {
            balance[account - 1] -= money;
            return true;
        }
        return false;
    }

    private boolean validAccountNumber(int account) {
        return account > 0 && account <= balance.length;
    }
}