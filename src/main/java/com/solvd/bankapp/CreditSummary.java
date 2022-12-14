package com.solvd.bankapp;


import java.util.Objects;

public class CreditSummary {

    private long salary;
    private long patrimony;
    private boolean isDefaulter;
    private boolean hasCredit;

    public CreditSummary(long salary, long patrimony, boolean isDefaulter, boolean hasCredit) {
        this.salary = salary;
        this.patrimony = patrimony;
        this.isDefaulter = isDefaulter;
        this.hasCredit = hasCredit;
    }

    public long getSalary() {
        return salary;
    }

    public boolean isDefaulter() {
        return isDefaulter;
    }

    public boolean hasCredit() {
        return hasCredit;
    }

    public void setHasCredit(boolean hasCredit) {
        this.hasCredit = hasCredit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hasCredit, isDefaulter, patrimony, salary);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CreditSummary other = (CreditSummary) obj;
        return hasCredit == other.hasCredit && isDefaulter == other.isDefaulter && patrimony == other.patrimony
                && salary == other.salary;
    }

    @Override
    public String toString() {
        return "CreditSummary [salary=" + salary + ", patrimony=" + patrimony + ", isDefaulter=" + isDefaulter
                + ", hasCredit=" + hasCredit + "]";
    }

}
