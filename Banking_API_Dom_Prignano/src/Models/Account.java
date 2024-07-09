package Models;

import java.util.List;

public class Account {

    // Important variables

    private User user;
    private double balance;

    // Constructors

    public Account (Integer userId, String accountName, BigDecimal balance) {
        this.userId = userId;
        this.accountName = accountName;
        this.balance = balance;
    }

    public Account() {}

    // Getters & Setters

    public Integer getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public double getBalance() { return balance; }

    public void setBalance(double balance) { this.balance = balance; }

}