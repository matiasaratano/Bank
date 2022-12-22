package com.solvd.bankapp.lambda;

public interface TriArgFunction<T, U, V, R> {

    R apply(T t, U u, V v);
}