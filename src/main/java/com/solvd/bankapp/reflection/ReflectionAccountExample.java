package com.solvd.bankapp.reflection;

public class ReflectionAccountExample {
    private String id;
    private String name;
    private boolean isDefaulter;
    private int balance;

    public ReflectionAccountExample() {
        this.id = "1";
        this.name = "Lionel Messi";
        this.isDefaulter = false;
        this.balance = 1000;
    }

    public ReflectionAccountExample(String id, String name, boolean isDefaulter, int balance) {
        this.id = id;
        this.name = name;
        this.isDefaulter = isDefaulter;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefaulter() {
        return isDefaulter;
    }

    public void setDefaulter(boolean defaulter) {
        isDefaulter = defaulter;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "ReflectionAccount{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isDefaulter=" + isDefaulter +
                ", balance=" + balance +
                '}';
    }
}