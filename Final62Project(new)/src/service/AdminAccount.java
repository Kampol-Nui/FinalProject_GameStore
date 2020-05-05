package service;

//import io.AllCustomer;

import account.Account;
import account.AccountStatus;
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
    
    public void setCustomerStatusTo(CustomerAccount ac,AccountStatus status){
        ac.setStatus(this,status);
    }
    

}
    

      
