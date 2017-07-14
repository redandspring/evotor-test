package ru.redandspring.evotortest.model;

/**
 * @author Alexander Tretyakov
 */
public class User extends BaseEntity
{

    public static final String TABLE_NAME = "users";

    private String login;
    private String password;
    private double balance;

    public User(){
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.balance = 0;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
