package service;

import game.Game;
import java.util.ArrayList;

public class Cart implements CustomerService {

    private double totalprice;
    protected ArrayList<Game> itemInCart;
    //private String[] gameName;

    public Cart() {
        itemInCart = new ArrayList<>();
    }

    public double getTotalprice() {
        return this.totalprice;
    }

    public double getEachGamePrice(int i) {
        return this.itemInCart.get(i).getSpecialPrice();
    }

    public void calculateTotalPrice() {
        for (int i = 0; i < this.itemInCart.size(); i++) {
            this.totalprice += this.itemInCart.get(i).getSpecialPrice();

        }

    }

    public boolean listGameFromCart() {
        System.out.println("************************ เกมในตระกร้าของฉัน *************************");
        if (itemInCart.isEmpty()) {
            System.out.println("คุณยังไม่มีเกมในตระกร้า");
            System.out.println("******************************************************************");
            return false;
        }
        for (Game game : this.itemInCart) {

            System.out.println(game + "\t");

        }
        System.out.println("******************************************************************");
        return true;

    }

    @Override
    public boolean addGameToCart(GameStore gameStore, String title) {
        try {
            for (int i = 0; i < gameStore.getGames().size(); i++) {
                if (gameStore.getGames().get(i).getTitle().equals(title)) {
                    //this.gameName[i] = gameStore.getGames().get(i).getTitle();
                    this.itemInCart.add(gameStore.getGames().get(i));
                    System.out.println("คุณได้เพิ่มเกม " + gameStore.getGames().get(i).getTitle() + " ลงตระกร้า!");
                    return true;
                }
                for (int j = 0; j < itemInCart.size(); j++) {
                    if (itemInCart.get(j).getTitle().equals(title)) {
                        System.out.println("คุณมีเกมนี้อยู่แล้วในตระกร้า");
                        return false;
                    }
                }

            }
        } catch (NullPointerException ex) {
            System.out.println("NullPointerException Caught");
            return false;

        }

        System.out.println("ไม่พบเกมที่คุณต้องการจะเพิ่มลงตระกร้า");
        return false;
    }

    @Override
    public boolean removeGameFromCart(String title) {
        try {
            for (int i = 0; i < this.itemInCart.size(); i++) {
                if (this.itemInCart.get(i).getTitle().equals(title)) {
                    this.itemInCart.remove(this.itemInCart.get(i));
                    System.out.println("คุณได้ลบเกม " + title + " ออกจากตระกร้า!");
                    return true;
                }

            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        System.out.println("ไม่พบเกมที่คุณต้องการจะลบในตระกร้า");
        return false;

    }

    public ArrayList<Game> getItemInCart() {
        return itemInCart;
    }

//    public String getGameName(int i) {
//        
//        return gameName[i];
//    }
}
