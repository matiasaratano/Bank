package com.solvd.bankapp.card;

public enum CardType {
    CREDIT("Credit", 10000, 150), DEBIT("Debit", 1000000, 50);
    private final String name;
    private int maxLimit;
    private int annualFee;


    CardType(String name, int maxLimit, int annualFee) {
        this.name = name;
        this.maxLimit = maxLimit;
        this.annualFee = annualFee;
    }

    public String getName() {
        return name;
    }

    public int getMaxLimit() {
        return this.maxLimit;
    }

    public int getAnnualFee() {
        return this.annualFee;
    }

    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }

    public void setAnnualFee(int annualFee) {
        this.annualFee = annualFee;
    }

    @Override
    public String toString() {
        return "CardType{" +
                "name='" + name + '\'' +
                ", maxLimit=" + maxLimit +
                ", annualFee=" + annualFee +
                '}';
    }
}