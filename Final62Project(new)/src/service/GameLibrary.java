package service;


import admin.io.ReadWritePurchaseHistoryTranscription;
import game.Game;
import dataaccess.DBmanager;
import java.util.ArrayList;

public class GameLibrary {

    private ArrayList<Game> myGameLibrary;
    private CustomerAccount ac;

    public GameLibrary(CustomerAccount ac) {
        this.ac = ac;
        this.myGameLibrary = new ArrayList<>();
    }

    public void addGameFromCartToLibrary() {
        if(DBmanager.checkRepeatGameInLibrary(ac) == false){
            System.out.println("คุณมีเกมนี้อยู่แล้ว");
                      
        }
        else if (ac.getMyCart().getTotalprice() <= DBmanager.SelectLastMoney(ac)) {
            ac.getMyCart().calculateTotalPrice();
            this.myGameLibrary = (ArrayList<Game>) ac.getMyCart().itemInCart.clone();
            double oldmoney = dataaccess.DBmanager.SelectLastMoney(ac);
            ac.myLastMoney = dataaccess.DBmanager.SelectLastMoney(ac) - ac.getMyCart().getTotalprice();
            DBmanager.PurchaseGame(ac);
            ac.getMyCart().itemInCart.clear();
            System.out.println("ยอดเงินก่อนชำระ : " + oldmoney + " ยอดเงินคงเหลือหลังชำระ : " + ac.getMyMoney());
            ReadWritePurchaseHistoryTranscription.writePurchaseHistory(ac);
        } else {
            System.out.println("จำนวนเงินในกระเป๋าของคุณไม่เพียงพอ โปรดเติมเงินของคุณ");
        }

    }

    public void listMyGameInLibrary(CustomerAccount ac) {
        try {
            System.out.println("************************ MY Library *************************");
             dataaccess.DBmanager.listRecentLibrary(ac);
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            
        }

    }

}
