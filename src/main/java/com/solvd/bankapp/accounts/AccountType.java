package com.solvd.bankapp.accounts;

public enum AccountType {

    CHECKING("Checking", 0.01),
    SAVINGS("Savings", 0.02);

    private final String name;
    private final double interestRate;

    AccountType(String name, double interestRate) {
        this.name = name;
        this.interestRate = interestRate;
    }

    public String getName() {
        return name;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double calculateInterest(double balance) {
        return balance * interestRate;
    }

}
