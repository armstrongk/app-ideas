package com.example.armstrong.college.transactions;

public class TransactionModel {
    String date;
    String amount;

    public TransactionModel(String date, String amount){
        this.date = date;
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getAmount() {
        return amount;
    }

}
