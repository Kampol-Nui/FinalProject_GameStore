package service;

import genarate.GetNextID;
import person.Person;
import account.Account;
import account.AccountStatus;
import dataaccess.DBmanager;

public class CustomerAccount extends Account {

    private long uniqueId;
    private Cart myCart;
    private GameLibrary myLibrary;
    private double topupMoney = 0;
    protected double myLastMoney;
    //public double myEachMoney;

    public CustomerAccount(String username, String password, AccountStatus status, Person person) {
        super(username, password, person);
        this.myCart = new Cart();
        this.myLibrary = new GameLibrary(this);
        if (DBmanager.selectLastCustomerID() == 1) {
            this.uniqueId = GetNextID.getNext();
        } else {
            this.uniqueId = DBmanager.selectLastCustomerID();
        }
        callKeepCustomerInfo();

    }

    private void callKeepCustomerInfo() {
        DBmanager.keepCustomerInfo(this);
    }

    public double getMyMoney() {
        return this.myLastMoney;
    }

    public double getTopupMoney() {
        return topupMoney;
    }

    public Cart getMyCart() {
        return this.myCart;
    }

    public void TopupMoney(double topupmoney) {
        this.topupMoney = topupmoney;
        if (this.topupMoney <= 0) {
            System.out.println("Please insert your money");

        } else {
            this.myLastMoney += this.topupMoney;
            DBmanager.topupMoney(this);
        }
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public void listBuyingHistory() {
        DBmanager.SelectTablePurchaseHistory(this);
    }

    public GameLibrary getMyLibrary() {
        return myLibrary;
    }

//    public double getMyEachMoney() {
//        return myEachMoney;
//    }
}
