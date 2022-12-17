package com.solvd.bankapp;


import com.solvd.bankapp.accounts.Account;
import com.solvd.bankapp.card.Card;
import com.solvd.bankapp.card.CardType;
import com.solvd.bankapp.card.Scheme;
import com.solvd.bankapp.exceptions.*;
import com.solvd.bankapp.persons.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Runner {
    private static final Logger LOGGER = LogManager.getLogger(Runner.class);

    public static void main(String[] args) throws IncorrectDetailException {

        Scanner scanner = new Scanner(System.in);
        Bank bank = initBank();

        boolean exitRequested = false;
        while (!exitRequested) {
            try {
                Menu.printMenu();
                int choice = Integer.parseInt(scanner.next());
                switch (choice) {
                    case 1:
                        try {
                            bank.registerAccount();
                        } catch (IncorrectNumberException e) {
                            LOGGER.warn(e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            bank.manageAccount();
                        } catch (InvalidAmountException | InsufficientFundsException | InvalidAccountException |
                                 IncorrectNumberException | IncorrectDetailException e) {
                            LOGGER.warn(e.getMessage());
                        }
                        break;
                    case 3:
                        bank.listAccounts();
                        break;
                    case 4:
                        bank.IPrintBasicOperations();
                        break;
                    case 5:
                        // Lambda function to filter the accounts by balance (bigger than 150)
                        ArrayList<Account> highBalanceAccounts = bank.filterAccounts(a -> a.getBalance() > 150.0);
                        for (Account account : highBalanceAccounts) {
                            LOGGER.info(account.getSummary() + ", Balance: " + account.getBalance());

                        }
                    case 6:
                        // Lambda function to update the balances of all accounts (duplicates it to test the method)
                        bank.updateAccountBalances(a -> (a.getBalance() * 2));
                        break;
                    case 7:
                        exitRequested = true;
                        break;
                    default:
                        LOGGER.warn("Wrong input");
                        break;
                }
            } catch (NumberFormatException e) {
                throw new CustomNumberFormatException("Invalid input. Please enter a valid number.");
            } catch (InputMismatchException e) {
                throw new CustomInputMismatchException("Invalid input.");
            }
        }
        scanner.close();

        // Lambda function to filter Accounts by address
        bank.filterAccountsByAdress(account -> account.getClient().getAddress().getCountry().equals("EEUU"));
        // Lambda function to get an Account Summary
        bank.getAccountSummaries(account -> "Account ID: " + account.getID() + ", Balance: " + account.getBalance());
        // Lambda function to update the balances of all accounts (+100)
        bank.updateAccounts(account -> account.setBalance(account.getBalance() + 100));
    }

    private static Bank initBank() throws IncorrectDetailException {

        Address address1 = new Address("EEUU", "CA", "Venice", "ABC", "123");
        Address address2 = new Address("EEUU", "CA", "Santa Monica", "BCD", "234");
        Address address3 = new Address("EEUU", "CA", "Venice", "CDE", "345");
        Bank bank = new Bank("bank1", address1);
        Card card1 = new Card("123456789", "202608", "202108", "John Florence", "BBVA", Scheme.MASTER_CARD,
                CardType.DEBIT);
        CreditSummary credSummary1 = new CreditSummary(4000, 50000, false, false);
        CreditSummary credSummary2 = new CreditSummary(3000, 150000, false, true);
        CreditSummary credSummary3 = new CreditSummary(2000, 200, true, false);
        Client client1 = new Client("John", "Florence", "1231231231", address2, credSummary1);
        Client client2 = new Client("Richard", "Rodriguez", "1234567899", address3, credSummary2);
        Client client3 = new Client("Mark", "Zucke", "1234567899", address3, credSummary3);
        Client client4 = new Client("Peter", "Terry", "1234567891", address3, credSummary1);
        client1.setCard(card1);
        bank.registerCheckingAccount(client1);
        bank.registerCheckingAccount(client2);
        bank.registerCheckingAccount(client3);
        bank.registerSavingsAccount(client4);
        bank.lookupAccount(4, "1234567891").setBalance(10000);
        return bank;


    }


}
