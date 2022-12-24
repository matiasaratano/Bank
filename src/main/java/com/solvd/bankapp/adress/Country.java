package com.solvd.bankapp.adress;

public enum Country {

    USA("Washington D.C."),
    CANADA("Ottawa"),
    MEXICO("Mexico City"),
    BRAZIL("Brasília"),
    ARGENTINA("Buenos Aires");


    private final String capital;

    Country(String capital) {
        this.capital = capital;

    }

    public String getCapital() {
        return capital;
    }

}