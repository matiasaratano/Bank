package com.solvd.bankapp;


import com.solvd.bankapp.accounts.Account;
import com.solvd.bankapp.adress.Address;
import com.solvd.bankapp.adress.Country;
import com.solvd.bankapp.adress.Province;
import com.solvd.bankapp.card.Card;
import com.solvd.bankapp.card.CardType;
import com.solvd.bankapp.card.Scheme;
import com.solvd.bankapp.exceptions.*;
import com.solvd.bankapp.persons.Client;
import com.solvd.connection.Connection;
import com.solvd.connection.CustomConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
                            LOGGER.info(account.getSummary());
                        }
                        break;
                    case 6:
                        Menu.updateBalance();
                        int newChoice = Integer.parseInt(scanner.next());
                        switch (newChoice) {
                            case 1:
                                // Lambda function to update the balances of all accounts (duplicates it to test the method)
                                bank.updateAccountBalances(a -> (a.getBalance() * 2));
                                LOGGER.info("Balances have been updated successfully");
                                break;
                            case 2:
                                // Lambda function to update the balances of all accounts (+100)
                                bank.updateAccounts(account -> account.setBalance(account.getBalance() + 100));
                                LOGGER.info("Balances have been updated successfully");
                                break;
                            case 3:
                                // Lambda function to perform a deposit operation (+50)
                                bank.performOperations((Account account, Double amount, String type) -> account.performOperation((double) 50, "deposit"));
                                break;
                            case 4:
                                Menu.printMenu();
                                break;
                            default:
                                LOGGER.warn("Invalid number");
                                break;
                        }
                        break;
                    case 7:
                        // Lambda function to filter Accounts by address
                        Country country = null;
                        Menu.countriesMenu();
                        int choices = Integer.parseInt(scanner.next());
                        switch (choices) {
                            case 1:
                                country = Country.ARGENTINA;
                                break;
                            case 2:
                                country = Country.BRAZIL;
                                break;
                            case 3:
                                country = Country.CANADA;
                                break;
                            case 4:
                                country = Country.USA;
                                break;
                            case 5:
                                country = Country.MEXICO;
                                break;
                            case 6:
                                Menu.printMenu();
                                break;
                            default:
                                LOGGER.warn("Invalid country selected");
                                break;
                        }
                        Country finalCountry = country;
                        if (country != null) {
                            List<Account> filteredAccounts = bank.filterAccountsByAdress(account -> account.getClient().getAddress().getCountry().equals(finalCountry));
                            for (Account account : filteredAccounts) {
                                LOGGER.info(account.getSummary());
                            }
                            if (filteredAccounts.size() == 0) {
                                LOGGER.info("Zero accounts in the selected country");
                            }
                        }
                        break;
                    case 8:
                        bank.streamTest();
                        break;
                    case 9:
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

        CustomConnectionPool pool = new CustomConnectionPool(5);
        // Create a thread pool with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // Create a Runnable task
        Runnable task1 = new Runnable() {
            public void run() {
                Connection connection = null;
                try {
                    connection = pool.getConnection();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Use the connection
                pool.releaseConnection(connection);
            }
        };

        // Create a Thread
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                Connection connection = null;
                try {
                    connection = pool.getConnection();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                // Use the connection
                pool.releaseConnection(connection);
            }
        });

        // Submit the tasks to the thread pool
        executor.submit(task1);
        executor.submit(task1);

        // Start the Thread
        thread1.start();

        // Shut down the thread pool
        executor.shutdown();
    }
    

    private static Bank initBank() throws IncorrectDetailException {

        Address address1 = new Address(Country.ARGENTINA, Province.BUENOS_AIRES, "Villa Gesell", "ABC", "123");
        Address address2 = new Address(Country.BRAZIL, Province.RIO, "Ipanema", "BCD", "234");
        Address address3 = new Address(Country.CANADA, Province.OTTAWA, "Toronto", "CDE", "345");
        Bank bank = new Bank("bank1", address1);
        Card card1 = new Card("123456789", "202608", "202108", "John Florence", "BBVA", Scheme.MASTER_CARD,
                CardType.DEBIT);
        Card card2 = new Card("123456789", "202608", "202108", "John Florence", "BBVA", Scheme.VISA,
                CardType.CREDIT);
        CreditSummary credSummary1 = new CreditSummary(4000, 50000, false, false);
        CreditSummary credSummary2 = new CreditSummary(3000, 150000, false, true);
        CreditSummary credSummary3 = new CreditSummary(2000, 200, true, false);
        Client client1 = new Client("John", "Florence", "1231231231", address2, credSummary1);
        Client client2 = new Client("Richard", "Rodriguez", "1234567899", address3, credSummary2);
        Client client3 = new Client("Mark", "Zucke", "1234567899", address3, credSummary3);
        Client client4 = new Client("Peter", "Terry", "1234567891", address3, credSummary1);
        client1.setCard(card1);
        client1.setCard(card2);
        bank.registerCheckingAccount(client1);
        bank.registerCheckingAccount(client2);
        bank.registerCheckingAccount(client3);
        bank.registerSavingsAccount(client4);
        bank.lookupAccount(4, "1234567891").setBalance(10000);
        return bank;

    }
}
