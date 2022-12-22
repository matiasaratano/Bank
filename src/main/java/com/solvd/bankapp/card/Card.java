package com.solvd.bankapp.card;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Card {

    private String cardNumber;
    private String dateExp;
    private String dateFrom;
    private String titular;
    private int cvv;
    private String bank;
    private Scheme scheme;
    private CardType type;


    public Card(String cardNumber, String dateExp, String dateFrom, String titular, String bank, Scheme scheme,
                CardType type) {
        this.cardNumber = cardNumber;
        this.dateExp = dateExp;
        this.dateFrom = dateFrom;
        this.titular = titular;
        this.bank = bank;
        this.scheme = scheme;
        this.type = type;
        this.cvv = ThreadLocalRandom.current().nextInt(100, 999);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bank, cardNumber, cvv, dateExp, dateFrom, titular, scheme, type);
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
        Card other = (Card) obj;
        return Objects.equals(bank, other.bank) && Objects.equals(cardNumber, other.cardNumber) && cvv == other.cvv
                && Objects.equals(dateExp, other.dateExp) && Objects.equals(dateFrom, other.dateFrom)
                && Objects.equals(titular, other.titular) && scheme == other.scheme && type == other.type;
    }

    @Override
    public String toString() {
        return "Card [cardNumber=" + cardNumber + ", dateExp=" + dateExp + ", dateFrom=" + dateFrom + ", titular="
                + titular
                + ", bank=" + bank + ", scheme=" + scheme.getName() + ", type=" + type.getName() + "]";
    }

}
