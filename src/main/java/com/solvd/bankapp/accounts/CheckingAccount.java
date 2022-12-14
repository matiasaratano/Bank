package com.solvd.bankapp.accounts;

import com.solvd.bankapp.Runner;
import com.solvd.bankapp.exceptions.InsufficientFundsException;
import com.solvd.bankapp.exceptions.InvalidAccountException;
import com.solvd.bankapp.exceptions.InvalidAmountException;
import com.solvd.bankapp.interfaces.IDeposit;
import com.solvd.bankapp.interfaces.ITransfer;
import com.solvd.bankapp.interfaces.IWithdrawal;
import com.solvd.bankapp.persons.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CheckingAccount extends Account implements IDeposit, IWithdrawal, ITransfer, Runnable {

    private static final AccountType accType = AccountType.CHECKING;
    private static final Logger LOGGER = LogManager.getLogger(Runner.class.getName());

    public CheckingAccount(Client client) {
        super(client);
    }


    @Override
    public void depositMoney(long depositAmount) throws InvalidAmountException {
        if (depositAmount < 0) {
            throw new InvalidAmountException("Enter a valid amount");
        } else {
            this.setBalance(getBalance() + depositAmount);
            LOGGER.info("You have deposit " + depositAmount + " to your account." + "\n" + "Balance is now: "
                    + this.getBalance());
        }
    }

    @Override
    public void withdrawal(long withdrawalAmount) throws InvalidAmountException, InsufficientFundsException {
        if (withdrawalAmount < 0) {
            throw new InvalidAmountException("Enter a valid amount");
        } else if (this.getBalance() < withdrawalAmount) {
            throw new InsufficientFundsException("Insufficient funds in the account");
        } else {
            this.setBalance(getBalance() - withdrawalAmount);
            LOGGER.info("You have withdrawal " + withdrawalAmount + " from your account." + "\n"
                    + "Balance is now: " + this.getBalance());
        }

    }

    @Override
    public void transferMoney(Account thisAccount, Account toAccount, long amountToTransfer) throws InvalidAmountException, InsufficientFundsException, InvalidAccountException {
        if (thisAccount.getID() == toAccount.getID()) {
            throw new InvalidAccountException("Enter a valid account");
        } else if (amountToTransfer < 0) {
            throw new InvalidAmountException("Enter a valid amount");
        } else if (thisAccount.getBalance() > amountToTransfer) {
            toAccount.setBalance(this.getBalance() + amountToTransfer);
            thisAccount.setBalance(this.getBalance() - amountToTransfer);
            LOGGER.info("You transferred: " + amountToTransfer + "\n"
                    + "Balance is now: " + this.getBalance());
        } else {
            throw new InsufficientFundsException("Insufficient funds in the account");
        }

    }

    @Override
    public String getSummary() {
        return "ID: " + getID() + ", " + "Phone: " + getPhoneNumber() + ", " + getClient().getFullName() + ", " + "Balance: " + getBalance() + ", Account Type: " + accType;
    }

    @Override
    public String toString() {
        return "Name: " + getFirstName() + "\n" + "Last name: " + getLastName() + "\n" + "Balance: " + getBalance()
                + "\n" + "ID: " + getID();
    }

    @Override
    public void run() {
        System.out.println("This code is running in a thread");
    }
}