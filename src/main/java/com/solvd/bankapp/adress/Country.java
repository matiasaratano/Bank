package com.solvd.bankapp.adress;

public enum Country {

    USA("United States"),
    CANADA("Canada"),
    MEXICO("Mexico"),
    BRAZIL("Brazil"),
    ARGENTINA("Argentina");


    private final String name;

    Country(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

}