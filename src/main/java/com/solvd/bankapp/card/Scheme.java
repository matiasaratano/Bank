package com.solvd.bankapp.card;

public enum Scheme {
    VISA, MASTER_CARD, AMERICAN_EXPRESS, DINERS_CLUB, JCB, DISCOVER;

    private String issuer;
    private String country;

    Scheme() {
        this("", "");
    }

    Scheme(String issuer, String country) {
        this.issuer = issuer;
        this.country = country;
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

    @Override
    public String toString() {
        return this.name() + " (issuer: " + this.issuer + ", country: " + this.country + ")";
    }
}