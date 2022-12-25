package com.solvd.bankapp.adress;

public enum Province {

    WASHINGTON("Washington D.C."), OTTAWA("Ottawa"), MEXICO_CITY("Mexico City"), RIO("Rio de Janeiro"), BUENOS_AIRES("Buenos Aires");

    private final String name;

    Province(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }
}
