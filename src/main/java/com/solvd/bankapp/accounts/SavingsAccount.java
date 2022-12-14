package com.solvd.bankapp.accounts;

import com.solvd.bankapp.accounts.Account;
import com.solvd.bankapp.accounts.AccountType;
import com.solvd.bankapp.exceptions.InsufficientFundsException;
import com.solvd.bankapp.exceptions.InvalidAmountException;
import com.solvd.bankapp.interfaces.IDeposit;
import com.solvd.bankapp.interfaces.IWithdrawal;
import com.solvd.bankapp.persons.Client;

public class SavingsAccount extends Account implements IDeposit, IWithdrawal {

    private static final AccountType accType = AccountType.SAVINGS;
    //Not implemented yet

    public SavingsAccount(Client client) {
        super(client);
    }


    @Override
    public String getSummary() {
        //Not implemented
        return " ";
    }

    @Override
    public void depositMoney(long depositAmount) throws InvalidAmountException {

    }


    @Override
    public void withdrawal(long withdrawalAmount) throws InvalidAmountException, InsufficientFundsException {

    }

    @Override
    public void transferMoney(Account thisAccount, Account toAccount, long amountToTransfer) throws InvalidAmountException, InsufficientFundsException {

    }

}
