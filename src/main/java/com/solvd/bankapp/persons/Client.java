package com.solvd.bankapp.persons;

import com.solvd.bankapp.Address;
import com.solvd.bankapp.CreditSummary;
import com.solvd.bankapp.card.Card;
import com.solvd.bankapp.linkedlist.CustomLinkedList;

import java.util.Objects;

public class Client extends Person {

    private CustomLinkedList<Card> cards;
    private CreditSummary creditSummary;


    public Client(String name, String lastName, String phoneNumber) {
        super(name, lastName, phoneNumber);
        this.cards = new CustomLinkedList<>();

    }

    public Client(String name, String lastName, String phoneNumber, Address address) {
        super(name, lastName, phoneNumber, address);
        this.cards = new CustomLinkedList<>();

    }


    public Client(String name, String lastName, String phoneNumber, Address address, CreditSummary creditSummary) {
        super(name, lastName, phoneNumber, address);
        this.creditSummary = creditSummary;
        this.cards = new CustomLinkedList<>();

    }

    public void setCard(Card card) {
        this.cards.add(card);
    }


    public Card getCard(int index) {

        return cards.get(index);
    }

    public CustomLinkedList<Card> getCards() {
        return cards;
    }

    public CreditSummary getCreditSummary() {
        return creditSummary;
    }

    public void setCreditSummary(CreditSummary creditSummary) {
        this.creditSummary = creditSummary;
    }

    @Override
    public String getFullName() {
        return "Name: " + getName() + " " + getLastName();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(cards, creditSummary, getPhoneNumber());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Client other = (Client) obj;
        return Objects.equals(cards, other.cards) && Objects.equals(creditSummary, other.creditSummary)
                && Objects.equals(getPhoneNumber(), other.getPhoneNumber());
    }

    @Override
    public String toString() {
        return "Client [name=" + this.getName() + ", lastName=" + this.getLastName() + ", address=" + this.getAddress()

                + ", phoneNumber=" + getPhoneNumber() + ", creditSummary=" + creditSummary + "]";
    }


}
