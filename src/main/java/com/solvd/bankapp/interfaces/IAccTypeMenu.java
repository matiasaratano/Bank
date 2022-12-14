package com.solvd.bankapp.interfaces;

import com.solvd.bankapp.Bank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface IAccTypeMenu {
    static final Logger LOGGER = LogManager.getLogger(Bank.class);

    public static void IAccTypeMenu() {
        LOGGER.info("What type of account do you want to register?" + "\n" +
                "\r" + "1.Checking" + "\n" +
                "\r" + "2.Savings" + "\n" +
                "3.Exit");
    }
}
