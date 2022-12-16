package com.solvd.bankapp;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Menu {

    private static final Logger LOGGER = LogManager.getLogger(Menu.class);

    public static void printMenu() {
        System.out.println("Hello, press: " + "\n" +
                "\r" + "1.Register" + "\n" +
                "\r" + "2.Log in" + "\n" +
                "\r" + "3.List Accounts Registered" + "\n" +
                "\r" + "4.Print Basic Operations" + "\n" +
                "\r" + "5.Exit");
    }

    public static void existAccountMenu() {
        System.out.println("What would you like to do?" + "\n" +
                "\r" + "1.Info" + "\n" +
                "\r" + "2.Deposit money" + "\n" +
                "\r" + "3.Withdrawal money" + "\n" +
                "\r" + "4.Money transferring" + "\n" +
                "\r" + "5.Get a Credit" + "\n" + "\r" + "6.Exit");
    }


}
