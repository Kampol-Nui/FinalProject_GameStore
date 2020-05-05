package service;

//import io.AllCustomer;
import account.Account;
import dataaccess.DBmanager;

//admin.writePurchaseHistory(cus2);
//admin.readPurchaseHistory("purchasehistory.dat",cus2);
public class AdminAccount extends Account {

    public AdminAccount(String username, String password) {
        super(username, password);

    }
    
    public void seeInfoOfAllCustomer(){
        DBmanager.seeDataofAllCustomer(this);
    }
}
    

      
