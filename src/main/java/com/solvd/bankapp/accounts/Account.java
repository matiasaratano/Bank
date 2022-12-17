package com.solvd.bankapp.accounts;

import com.solvd.bankapp.interfaces.IDeposit;
import com.solvd.bankapp.interfaces.ITransfer;
import com.solvd.bankapp.interfaces.IWithdrawal;
import com.solvd.bankapp.persons.Client;

public abstract class Account implements IDeposit, IWithdrawal, ITransfer {
    private long balance;
    private static int uid = 0;
    private final int id;
    private final Client client;


    public Account(Client client) {
        this.client = client;
        //balance = new Money();   Currency?
        this.balance = 0;
        uid++;
        this.id = uid;
    }

    public String getFirstName() {
        return this.client.getName();
    }

    public String getLastName() {
        return this.client.getLastName();
    }

    public long getBalance() {
        return this.balance;
    }

    public void setBalance(long newBalance) {
        this.balance = newBalance;
    }

    public int getID() {
        return this.id;
    }

    public String getPhoneNumber() {
        return this.client.getPhoneNumber();
    }

    public Client getClient() {
        return client;
    }

    public abstract String getSummary();

  
}
