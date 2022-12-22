package com.solvd.bankapp.card;

public enum Scheme {
    VISA("Visa"), MASTER_CARD("Master Card"), AMERICAN_EXPRESS("American Express"), DINERS_CLUB("Diners Club"), JCB("Jcb"), DISCOVER("Discover");


    private String name;
    private String issuer;
    private String country;

    Scheme(String name) {
        this.name = name;
    }

    Scheme(String name, String issuer, String country) {
        this.name = name;
        this.issuer = issuer;
        this.country = country;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIssuer() {
        return this.issuer;
    }

    public String getCountry() {
        return this.country;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
}