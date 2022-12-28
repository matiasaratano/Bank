package com.solvd.bankapp;


public class Menu {

    public static void printMenu() {
        System.out.println("Hello, press: " + "\n" +
                "\r" + "1.Register" + "\n" +
                "\r" + "2.Log in" + "\n" +
                "\r" + "3.List Accounts Registered" + "\n" +
                "\r" + "4.Print Basic Operations" + "\n" +
                "\r" + "5.Print High Balance Accounts" + "\n" +
                "\r" + "6.Update Account Balances" + "\n" +
                "\r" + "7.Filter Accounts By Country" + "\n" +
                "\r" + "8.Test Streams" + "\n" +
                "\r" + "9.Exit");
    }

    public static void existAccountMenu() {
        System.out.println("What would you like to do?" + "\n" +
                "\r" + "1.Info" + "\n" +
                "\r" + "2.Deposit money" + "\n" +
                "\r" + "3.Withdrawal money" + "\n" +
                "\r" + "4.Money transferring" + "\n" +
                "\r" + "5.Get a Credit" + "\n" + "\r" +
                "\r" + "6.Print Cards" + "\n" + "\r" + "7.Exit");
    }

    public static void countriesMenu() {
        System.out.println("Select Country" + "\n" +
                "\r" + "1.Argentina" + "\n" +
                "\r" + "2.Brazil" + "\n" +
                "\r" + "3.Canada" + "\n" +
                "\r" + "4.USA" + "\n" +
                "\r" + "5.Mexico" + "\n" + "\r" +
                "\r" + "6.Back");
    }

    public static void updateBalance() {
        System.out.println("Select Option:" + "\n" +
                "\r" + "1.Double balances" + "\n" +
                "\r" + "2.Add 100 to accounts balances " + "\n" +
                "\r" + "3.Deposit 50 to accounts balances " + "\n" +
                "\r" + "4.Back");
    }
}
