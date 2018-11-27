package com.skrzymo;


public class KontoDeb extends KontoHis {

    public KontoDeb(String accountNumber, double balance, String customerName) {
        super(accountNumber, balance, customerName);
    }

    public void debit(double amount, String accountNumber) {
        checkSize(accountNumber);
        addTransaction(new Transaction(accountNumber, "Debit", amount, this.balance - amount));
    }

    public boolean isDebitPossible(double amount, String accountNumber) {
        if (amount > (this.balance + maxDebitAmount(accountNumber))) {
            return false;
        }
        return true;
    }

    public double getAvgBalance(String accountNumber) {
        double fullBalance = 0;
        int counter = 0;
        for (Transaction t : KontoHis.getTransactions()) {
            if (t.getAccountNumber().equals(accountNumber)) {
                fullBalance += t.getActualBalance();
                counter++;
            }
        }
        return fullBalance / counter;
    }

    public double maxDebitAmount(String accountNumber) {
        return getAvgBalance(accountNumber) / 2;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
