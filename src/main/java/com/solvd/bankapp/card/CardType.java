package com.solvd.bankapp.card;

public enum CardType {
    CREDIT, DEBIT;

    private int maxLimit;
    private int annualFee;

    CardType() {
        // default values for maxLimit and annualFee
        this(0, 0);
    }

    CardType(int maxLimit, int annualFee) {
        this.maxLimit = maxLimit;
        this.annualFee = annualFee;
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
        return this.name() + " (max limit: " + this.maxLimit + ", annual fee: " + this.annualFee + ")";
    }
}