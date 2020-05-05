package service;


import person.Person;
import account.Account;
import account.AccountStatus;
import admin.io.ReadWritePurchaseHistoryTranscription;
import dataaccess.DBmanager;

public class CustomerAccount extends Account {

    private long uniqueId;
    private Cart myCart;
    private GameLibrary myLibrary;
    private double topupMoney = 0;
    protected double myLastMoney;
    //public double myEachMoney;

//    public CustomerAccount(String username, String password, AccountStatus status, Person person) {
//        super(username, password, person);
//        this.myCart = new Cart();
//        this.myLibrary = new GameLibrary(this);
//        this.genID();
//
//    }

    public CustomerAccount(String username, String password, AccountStatus status) {
        super(username, password);
        this.myCart = new Cart();
        this.myLibrary = new GameLibrary(this);
        
        
    }

    public CustomerAccount(long id, String username, String password, AccountStatus accountStatus) {
        super(username, password);
        this.myCart = new Cart();
        this.myLibrary = new GameLibrary(this);
        this.uniqueId = id;
    }

    
    public void genID(){
        if(DBmanager.incrementLastCustomerID()==1){
            
            
                this.uniqueId = 100000000;
            DBmanager.keepCustomerInfo(this);
        }else{
            
            
                this.uniqueId=DBmanager.incrementLastCustomerID();
            DBmanager.keepCustomerInfo(this);
        }
        //this.uniqueId = 1000000;
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
    public void customerReadPurchaseHistoryOf() {
        ReadWritePurchaseHistoryTranscription.readPurchaseHistory(this);
    }

}
