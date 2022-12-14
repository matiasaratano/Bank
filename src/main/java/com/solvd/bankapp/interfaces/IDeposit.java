package com.solvd.bankapp.interfaces;

import com.solvd.bankapp.exceptions.InvalidAmountException;

public interface IDeposit {

    public void depositMoney(long depositAmount) throws InvalidAmountException;
}
