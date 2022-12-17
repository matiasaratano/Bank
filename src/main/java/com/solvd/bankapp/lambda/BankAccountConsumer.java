package com.solvd.bankapp.lambda;

@FunctionalInterface
public interface BankAccountConsumer<T> {
    void accept(T t);
}