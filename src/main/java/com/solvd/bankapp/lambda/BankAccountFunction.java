package com.solvd.bankapp.lambda;

//FunctionalInterface
public interface BankAccountFunction<T, R> {
    R apply(T t);
}