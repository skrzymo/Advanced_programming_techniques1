package com.skrzymo;

public class Main {

    public static void main(String[] args) {
        FizzBuzz.fizzBuzz();

        Konto konto1 = new Konto("123456789",0,"Rafał");
        KontoHis  konto4 = new KontoHis("123564987",0.0,"Piotr");
        konto1.depositFunds(80.0,"123456789");

        konto1.withdrawalFunds(40,"123456789");
        konto1.depositFunds(50.0,"123456789");
        konto1.withdrawalFunds(40,"123456789");
        konto1.depositFunds(50.0,"123456789");
        konto1.withdrawalFunds(110,"123456789");
        konto1.withdrawalFunds(10,"123456789");
        konto1.depositFunds(50.0,"123456789");
        konto1.withdrawalFunds(40,"123456789");
        konto1.depositFunds(50.0,"123456789");
        konto1.withdrawalFunds(40,"123456789");
        konto1.depositFunds(50.0,"123456789");
        konto1.checkBalance();
        konto1.withdrawalFunds(70,"123456789");
        konto1.depositFunds(50.0,"123456789");
        konto1.withdrawalFunds(40,"123456789");
        konto1.depositFunds(50.0,"123456789");
        konto1.withdrawalFunds(40,"123456789");
        konto1.depositFunds(50.0,"123456789");
        konto1.withdrawalFunds(40,"123456789");
        konto1.depositFunds(50.0,"123456789");
        konto1.withdrawalFunds(40,"123456789");
        konto1.checkTransactions("123456789");
        konto1.depositFunds(50.0,"123456789");
        konto1.checkTransactions("123456789");


        Konto konto2 = new Konto("987654321",-20.0,"Ula");
    }
}
