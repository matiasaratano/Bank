package com.solvd.bankapp;

import com.solvd.bankapp.accounts.Account;
import com.solvd.bankapp.accounts.CheckingAccount;
import com.solvd.bankapp.accounts.SavingsAccount;
import com.solvd.bankapp.adress.Address;
import com.solvd.bankapp.exceptions.*;
import com.solvd.bankapp.interfaces.IAccTypeMenu;
import com.solvd.bankapp.interfaces.IPrintBasicOperations;
import com.solvd.bankapp.lambda.BankAccountConsumer;
import com.solvd.bankapp.lambda.BankAccountPredicate;
import com.solvd.bankapp.lambda.TriArgFunction;
import com.solvd.bankapp.persons.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bank extends Thread implements IPrintBasicOperations {

    private String name;
    private Address address;
    private ArrayList<Account> accounts;
    private Scanner scanner;

    private static final Logger LOGGER = LogManager.getLogger(Bank.class);

    public Bank(String name, Address address) {
        this.name = name;
        this.address = address;
        accounts = new ArrayList<>();
        scanner = new Scanner(System.in);

    }

    public Account lookupAccount(int accountID, String phoneNumber) throws IncorrectDetailException {
        for (Account account : accounts) {
            if (account.getID() == accountID && account.getPhoneNumber().equals(phoneNumber)) {
                return account;
            }
        }
        throw new IncorrectDetailException("One of the details is incorrect");
    }

    public Account lookupAccount(String phoneNumber) throws IncorrectDetailException {
        for (Account account : accounts) {
            if (account.getPhoneNumber().equals(phoneNumber)) {
                return account;
            }
        }
        throw new IncorrectDetailException("One of the details is incorrect");

    }

    public void listAccounts() {
        Stream<Account> accountStream = accounts.stream();
        Stream<String> summaryStream = accountStream.map(a -> a.getSummary());
        summaryStream.forEach(s -> System.out.println(s));
    }

    public void registerCheckingAccount(Client client) {
        accounts.add(new CheckingAccount(client));
    }

    public void registerSavingsAccount(Client client) {
        accounts.add(new SavingsAccount(client));
    }

    public void registerAccount() throws IncorrectNumberException {
        try {
            System.out.println("First name?");
            String firstName = scanner.next();
            System.out.println("Last name?");
            String lastName = scanner.next();
            System.out.println("Phone number?");
            String phoneNumber = scanner.next();
            if (isPhoneNumberCorrect(phoneNumber)) {
                boolean exitRequested = false;
                while (!exitRequested) {
                    IAccTypeMenu.printAccTypeMenu();
                    int choice = Integer.parseInt(scanner.next());
                    Client client = new Client(firstName, lastName, phoneNumber);
                    switch (choice) {
                        case 1:
                            accounts.add(new CheckingAccount(client));
                            LOGGER.info("You have created a checking account successfully!" + "\n" + "Your account ID is: "
                                    + accounts.get(accounts.size() - 1).getID());
                            exitRequested = true;
                            break;
                        case 2:
                            accounts.add(new SavingsAccount(client));
                            LOGGER.info("You have created a savings account successfully!" + "\n" + "Your account ID is: "
                                    + accounts.get(accounts.size() - 1).getID());
                            exitRequested = true;
                            break;
                        case 3:
                            exitRequested = true;
                            break;
                        default:
                            LOGGER.warn("Wrong input");
                            break;
                    }
                }
            }
        } catch (IncorrectNumberException ex) {
            throw new IncorrectNumberException("Phone number must be 10 digits.");
        }

    }

    public Account readAccount() throws IncorrectDetailException {
        Account selectedAccount = null;
        while (selectedAccount == null) {
            System.out.println("Please enter your ID:");
            int accountID = scanner.nextInt();
            System.out.println("Please enter your phone number:");
            String phoneNumber = scanner.next();
            selectedAccount = lookupAccount(accountID, phoneNumber);
        }
        return selectedAccount;

    }

    public static boolean isPhoneNumberCorrect(String phoneNumber) throws IncorrectNumberException {
        if (phoneNumber.length() != 10) {
            throw new IncorrectNumberException("Phone number must be 10 digits.");
        } else {
            return true;
        }
    }

    public void manageAccount() throws InvalidAmountException, InsufficientFundsException, InvalidAccountException, IncorrectNumberException, IncorrectDetailException {
        Account selectedAccount = this.readAccount();
        boolean exitRequested = false;
        while (!exitRequested) {
            Menu.existAccountMenu();
            int choice = Integer.parseInt(scanner.next());
            switch (choice) {
                case 1:
                    LOGGER.info(selectedAccount.getSummary());
                    break;
                case 2:
                    System.out.println("Please enter deposit amount:");
                    long depositAmount = (long) scanner.nextDouble();
                    selectedAccount.depositMoney(depositAmount);
                    break;
                case 3:
                    System.out.println("Please enter withdrawal amount:");
                    long withdrawalAmount = (long) scanner.nextDouble();
                    selectedAccount.withdrawal(withdrawalAmount);
                    break;
                case 4:
                    System.out.println("Please enter the phone number of the account you want to transfer to: ");
                    String accountPhoneNumber = scanner.next();
                    if (isPhoneNumberCorrect(accountPhoneNumber)) {
                        Account accountToTransfer = lookupAccount(accountPhoneNumber);
                        System.out.println("Enter the amount of money you would like to transfer:");
                        long moneyToTransfer = (long) scanner.nextDouble();
                        selectedAccount.transferMoney(selectedAccount, accountToTransfer, moneyToTransfer);
                    }
                    break;
                case 5:
                    CreditSummary creditSum = selectedAccount.getClient().getCreditSummary();
                    long creditDepositAmount = this.getCredit(creditSum);
                    if (creditSum != null && isApproved(creditSum, creditDepositAmount)) {
                        creditSum.setHasCredit(true);
                        selectedAccount.depositMoney(creditDepositAmount);
                    }
                    break;
                case 6:
                    for (int i = 0; i < selectedAccount.getClient().getCards().size(); i++) {
                        LOGGER.info(selectedAccount.getClient().getCard(i));
                    }
                    break;
                case 7:
                    exitRequested = true;
                    break;
                default:
                    LOGGER.warn("Wrong input");
                    break;
            }

        }
    }

    private long getCredit(CreditSummary creditSummary) {
        long creditAmount;
        try {
            creditAmount = 0;
            if (creditSummary == null) {
                LOGGER.info("Credit Refused, no credit summary in your account, update it at your nearest bank");
            } else {
                do {
                    if (creditAmount > creditSummary.getSalary() * 10) {
                        LOGGER.info("You cant get more than 10 salaries");
                    }
                    System.out.println("Enter the amount (up to 10 salaries) or 0 to close");
                    creditAmount = (long) scanner.nextDouble();
                } while (creditAmount < 0 || creditAmount > creditSummary.getSalary() * 10);
            }
        } catch (RuntimeException e) {
            throw new CustomInputMismatchException("Wrong input");
        }
        return creditAmount;
    }

    public boolean isApproved(CreditSummary creditSummary, long creditAmount) {
        boolean isApproved = false;
        if (creditAmount == 0) {
            LOGGER.info("Exit");
        } else if (!creditSummary.isDefaulter() && !creditSummary.hasCredit() && creditSummary.getSalary() > 0) {
            isApproved = true;
            LOGGER.info("Credit Approved");
        } else if (creditSummary.isDefaulter()) {
            LOGGER.info("Credit denied because of a default, call the bank to find out your credit status");
        } else if (creditSummary.hasCredit()) {
            LOGGER.info("Credit denied because you already have another one");
        } else if (creditSummary.getSalary() > 0) {
            LOGGER.info("Credit denied because of your stated salary, call the bank to update it");
        } else {
            LOGGER.info("Credit Denied");
        }
        return isApproved;
    }


    @Override
    public void IPrintBasicOperations() {
        HashMap<String, String> map = new HashMap<>();
        map.put("Deposit", "A deposit is when you add money to your account.");
        map.put("Withdrawal", "A withdrawal is when you take money out of your account.");
        map.put("Transfer", "A transfer is when you move money from one account to another.");
        LOGGER.info("Basic bank operations: ");
        for (String key : map.keySet()) {
            String value = map.get(key);
            LOGGER.info(key + ": " + value);
        }
    }


    public ArrayList<Account> filterAccounts(Predicate<Account> predicate) {
        ArrayList<Account> filteredAccounts = new ArrayList<>();
        for (Account account : accounts) {
            if (predicate.test(account)) {
                filteredAccounts.add(account);
            }
        }
        return filteredAccounts;
    }

    public void updateAccountBalances(Function<Account, Long> mapper) {
        for (Account account : accounts) {
            long newBalance = mapper.apply(account);
            account.setBalance(newBalance);
        }
    }

    public List<Account> filterAccountsByAddress(BankAccountPredicate<Account> predicate) {
        List<Account> filteredAccounts = new ArrayList<>();
        for (Account account : this.accounts) {
            if (predicate.test(account)) {
                filteredAccounts.add(account);
            }
        }
        return filteredAccounts;
    }

    public void updateAccounts(BankAccountConsumer<Account> consumer) {
        for (Account account : this.accounts) {
            consumer.accept(account);
        }
    }

    public void performOperations(TriArgFunction<Account, Double, String, Boolean> operation) {
        for (Account account : this.accounts) {
            if (operation.apply(account, 100.0, "withdraw")) {
                LOGGER.info("Successfully performed operation on account: " + account.getID());
            } else {
                LOGGER.info("Failed to perform operation on account: " + account.getID());
            }
        }
    }

    public void streamTest() {
        Stream<Account> accountStream = accounts.stream();
        //non-terminal operation to filter the stream to only include accounts with a balance greater than 1000
        Stream<Account> filteredAccountStream = accountStream.filter(a -> a.getBalance() > 1000);
        //non-terminal operation to transform the stream of accounts into a stream of account holder last name
        Stream<String> namesStream = filteredAccountStream.map(a -> a.getLastName());
        //terminal operation to collect the stream into a list and print the list
        List<String> namesList = namesStream.collect(Collectors.toList());
        System.out.println(namesList);
    }

    @Override
    public void run() {
        System.out.println("This code is running in a thread");
    }
}