package com.solvd.bankapp.persons;

import com.solvd.bankapp.adress.Address;

import java.util.Objects;

public abstract class Person {

    private String name;
    private String lastName;
    private Address address;
    private String phoneNumber;

    protected Person(String name, String lastName, String phoneNumber) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    protected Person(String name, String lastName, String phoneNumber, Address address) {
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }

    public abstract String getFullName();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", lastName=" + lastName + ", address=" + address + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, lastName, name);
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
        Person other = (Person) obj;
        return Objects.equals(address, other.address) && Objects.equals(lastName, other.lastName)
                && Objects.equals(name, other.name);
    }


}
