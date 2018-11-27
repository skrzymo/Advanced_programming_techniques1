package com.skrzymo;

import java.util.concurrent.atomic.AtomicInteger;

public class Transaction {

    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;
    private String accountNumber;
    private String transactionType;
    private double amount;
    private double actualBalance;

    public Transaction(String accountNumber, String transactionType, double amount, double actualBalance) {
        this.id = count.incrementAndGet();
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.actualBalance = actualBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getActualBalance() {
        return this.actualBalance;
    }

    public String toString() {
        return "\n******\nID " + id + "\nType of transaction: " + transactionType + "\nAmount: " + amount + "\nBalance after transaction: " + actualBalance + "\n******";
    }
}
