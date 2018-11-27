package com.skrzymo;

import java.util.LinkedList;
import java.util.List;

public class KontoHis extends Konto {

    private static List<Transaction> transactions = new LinkedList<Transaction>();

    public KontoHis(String accountNumber, double balance, String customerName) {
        super(accountNumber, balance, customerName);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public static void deleteTransaction(Transaction t) {
        transactions.remove(t);
    }

    public static List<Transaction> getTransactions() {
        return transactions;
    }
}
