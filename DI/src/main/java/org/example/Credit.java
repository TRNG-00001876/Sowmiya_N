package org.example;

public class Credit implements Ipayment {

    @Override
    public boolean processPayment(double billAnt) {
        System.out.println("credit card");
        return true;
    }
}
