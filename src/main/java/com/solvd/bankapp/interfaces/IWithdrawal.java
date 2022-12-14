package com.solvd.bankapp.interfaces;

import com.solvd.bankapp.exceptions.InsufficientFundsException;
import com.solvd.bankapp.exceptions.InvalidAmountException;

public interface IWithdrawal {

    public void withdrawal(long withdrawalAmount) throws InvalidAmountException, InsufficientFundsException;
}
