package com.solvd.bankapp.lambda;

//FunctionalInterface
public interface BankAccountPredicate<T> {
    boolean test(T t);
}