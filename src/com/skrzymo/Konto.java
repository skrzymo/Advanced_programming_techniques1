package com.skrzymo;

import java.util.LinkedList;
import java.util.List;

public class Konto {

    protected String accountNumber;
    protected double balance;
    protected String customerName;
    private List<KontoHis> historyAccounts = new LinkedList<KontoHis>();
    private List<KontoDeb> debitAccounts = new LinkedList<KontoDeb>();


    public Konto(String accountNumber, double balance, String customerName) {
        try {
            if (balance < 0) {
                throw new NegativeBalanceException();
            }
            this.accountNumber = accountNumber;
            this.balance = balance;
            this.customerName = customerName;
        }catch (NegativeBalanceException e) {
            System.out.println("Error: Can't create new account. Negative account balance.");
        }
    }

    public Konto(String accountNumber, String customerName) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = 0.0;
    }

    public void depositFunds(double amount, String accountNumber) {
        try{
            if(amount < 0) {
                throw new NegativeAmountException();
            }
                this.balance = this.balance + amount;
                System.out.println("Deposit of " + amount + " made. New balance = " + this.balance);
                KontoHis k = findHistoryAccount(accountNumber);
                if(k == null) {
                    KontoHis k2 = new KontoHis(accountNumber,this.balance,this.customerName);
                    addHistoryAccount(k2);
                    k2.addTransaction(new Transaction(accountNumber,"Deposit",amount,this.balance));
                } else {
                    checkSize(accountNumber);
                    k.addTransaction(new Transaction(accountNumber,"Deposit", amount, this.balance));
                    updateBalance(accountNumber);
                }
        } catch (NegativeAmountException e) {
            System.out.println("Error deposit: negative amount.");
        }
    }

    public void withdrawalFunds(double amount, String accountNumber) {
        try {
            if(amount < 0) {
                throw new NegativeAmountException();
            } else if(this.balance < amount && findDebitAccount(accountNumber) == null) {
                throw new OverdraftException();
            }else if(this.balance < amount && findDebitAccount(accountNumber) != null) {
                if(!isDebitPossible(amount,accountNumber)) {
                    throw new ToLowDebitLimitException();
                }
                getDebit(amount,accountNumber);
                this.balance = this.balance - amount;
                System.out.println("Withdrawal of " + amount + " processed. Remaining balance = " + this.balance);
            }else {
                this.balance = this.balance - amount;
                System.out.println("Withdrawal of " + amount + " processed. Remaining balance = " + this.balance);
                KontoHis k = findHistoryAccount(accountNumber);
                if (k == null) {
                    KontoHis k2 = new KontoHis(accountNumber, this.balance, this.customerName);
                    addHistoryAccount(k2);
                    k2.addTransaction(new Transaction(accountNumber, "Withdrawal", amount, this.balance));
                } else {
                    checkSize(accountNumber);
                    k.addTransaction(new Transaction(accountNumber, "Withdrawal", amount, this.balance));

                }
            }
        } catch(OverdraftException e) {
            System.out.println("Error: Only " + this.balance + " available. Withdrawal not processed.");
        } catch(NegativeAmountException e) {
            System.out.println("Error deposit: negative amount.");
        } catch (ToLowDebitLimitException e) {
            System.out.println("Error: Your debit limit is too low.");
        }
    }

    public void checkBalance(){
        System.out.println("Actual balance = " + this.balance);
    }

    public KontoHis findHistoryAccount(String accountNumber) {
        for (KontoHis k: historyAccounts) {
            if (accountNumber.equals(k.getAccountNumber()))
                return k;
        }
        return null;
    }
    public void checkTransactions(String accountNumber) {
        System.out.println("\nLIST OF TRANSACTIONS:");
        for (Transaction t : KontoHis.getTransactions()) {
            if(t.getAccountNumber().equals(accountNumber)) {
                System.out.println(t);
            }
        }
    }

    public void checkSize(String accountNumber) {
        List<Transaction> t1 = new LinkedList<Transaction>();
        for(Transaction t : KontoHis.getTransactions()) {
            if(t.getAccountNumber().equals(accountNumber)) {
                t1.add(t);
            }
        }
        if(t1.size() == 4) {
            debitAccounts.add(new KontoDeb(accountNumber,this.balance,this.customerName));
            updateBalance(accountNumber);
        }
        if(t1.size() == 20) {
            KontoHis.deleteTransaction(((LinkedList<Transaction>) t1).getFirst());
        }
    }

    public void addHistoryAccount(KontoHis k) {
        historyAccounts.add(k);
    }

    public void getDebit(double amount, String accountNumber) {
        for(KontoDeb k : debitAccounts) {
            if(k.getAccountNumber().equals(accountNumber)) {
                k.debit(amount,accountNumber);
            }
        }
    }

    public boolean isDebitPossible(double amount, String accountNumber) {
        for(KontoDeb k : debitAccounts) {
            if(k.getAccountNumber().equals(accountNumber)) {
               return k.isDebitPossible(amount, accountNumber);
            }
        }
        return false;
    }

    public KontoDeb findDebitAccount(String accountNumber) {
        for (KontoDeb k: debitAccounts) {
            if (k.getAccountNumber().equals(accountNumber))
                return k;
        }
        return null;
    }

    public void updateBalance(String accountNumber) {
        for(KontoDeb k : debitAccounts) {
            if(k.getAccountNumber().equals(accountNumber)) {
                k.setBalance(this.balance);
            }
        }
    }
}
