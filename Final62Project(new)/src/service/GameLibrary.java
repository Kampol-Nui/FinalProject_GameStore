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
        if(DBmanager.checkRepeatGameInLibrary(ac) == -1){
            System.out.println("คุณมีเกมนี้อยู่แล้ว");
                      
        }else if(DBmanager.checkRepeatGameInLibrary(ac) == 0){
            System.out.println("คุณไม่มีเกมที่จะเพิ่มลงในไลบราลี่");
        }
        else if (ac.getMyCart().getTotalprice() <= DBmanager.selectLastMoney(ac)) {
            ac.getMyCart().calculateTotalPrice();
            this.myGameLibrary = (ArrayList<Game>) ac.getMyCart().itemInCart.clone();
            double oldmoney = dataaccess.DBmanager.selectLastMoney(ac);
            ac.myLastMoney = dataaccess.DBmanager.selectLastMoney(ac) - ac.getMyCart().getTotalprice();
            DBmanager.purchaseGame(ac);
            ReadWritePurchaseHistoryTranscription.writePurchaseHistory(ac);
            ac.getMyCart().itemInCart.clear();
            System.out.println("ยอดเงินก่อนชำระ : " + oldmoney + " ยอดเงินคงเหลือหลังชำระ : " + ac.getMyMoney());
        } else {
            System.out.println("จำนวนเงินในกระเป๋าของคุณไม่เพียงพอ โปรดเติมเงินของคุณ");
        }

    }



}
