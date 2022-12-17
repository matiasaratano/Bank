package com.solvd.bankapp;


import java.util.Objects;

public class Address {

    private String country;
    private String province;
    private String town;
    private String streetAddress;
    private String zipCode;

    public Address(String country, String province, String town, String streetAddress, String zipCode) {
        this.country = country;
        this.province = province;
        this.town = town;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, province, streetAddress, town, zipCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Address other = (Address) obj;
        return Objects.equals(country, other.country) && Objects.equals(province, other.province)
                && Objects.equals(streetAddress, other.streetAddress) && Objects.equals(town, other.town)
                && Objects.equals(zipCode, other.zipCode);
    }

    @Override
    public String toString() {
        return "Address [country=" + country + ", province=" + province + ", town=" + town + ", streetAddress="
                + streetAddress + ", zipCode=" + zipCode + "]";
    }

    public String getCountry() {
        return country;
    }
}
