package com.solvd.bankapp.interfaces;

import com.solvd.bankapp.accounts.Account;
import com.solvd.bankapp.exceptions.InsufficientFundsException;
import com.solvd.bankapp.exceptions.InvalidAccountException;
import com.solvd.bankapp.exceptions.InvalidAmountException;

public interface ITransfer {

    public void transferMoney(Account thisAccount, Account toAccount, long amountToTransfer) throws InvalidAmountException, InsufficientFundsException, InvalidAccountException;
}
