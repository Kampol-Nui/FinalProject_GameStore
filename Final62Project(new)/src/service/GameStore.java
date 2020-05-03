package service;

import game.GameStatus;
import game.Game;
import java.util.ArrayList;
import java.util.Objects;

public class GameStore implements AdminService {

    private ArrayList<Game> gameStore;
    private GameStatus gamestatus;
    private String name;
    //private double[] 
    public GameStore(String name) {
        this.name = name;
        gameStore = new ArrayList<>();

    }

    public boolean listGameFromStore() {
        System.out.println("************************ เกมในร้านค้า " + this + " *************************");
        if (this.gameStore.isEmpty()) {
            System.out.println("ร้านค้ายังไม่มีเกม");
            return false;
        }
        for (Game game : gameStore) {
            System.out.println(game + "\t");
            
        }
        System.out.println("------------------------------------------------------------------------------");
        return true;
    }

    public ArrayList<Game> getGames() {
        return gameStore;
    }

    @Override
    public boolean addGameFrom(AdminAccount admin, Game game) {
        try {
            Objects.requireNonNull(admin, "AdminAccount cannot be null");
            if (game == null) {
                System.out.println("โปรดป้อนข้อมูลที่ไม่ใช่ Null");
                return false;
            }
            for (int i = 0; i < this.gameStore.size(); i++) {
                if (this.gameStore.get(i).equals(game)) {
                    System.out.println("มีเกมนี้อยู่แล้วบนร้านค้า " + this.name);
                    return false;
                }
            }
            game.setStatus(GameStatus.ONSALED);
            this.gameStore.add(game);
            System.out.println("คุณได้เพิ่มเกม " + game.getTitle() + " ไปยังร้านค้า " + this.name + "");
            return true;
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());

        }
        return false;
    }

   
    public GameStatus changeGameStatusFrom(AdminAccount admin, String title, GameStatus status) {
        try {
            for (int i = 0; i < this.gameStore.size(); i++) {
                if (this.gameStore.get(i).getTitle().equals(title)) {
                    this.gameStore.get(i).setStatus(status);
                     if(status==GameStatus.ONSALED){
                         this.gameStore.get(i).setPrice(this.gameStore.get(i).getNormalprice());
                         System.out.print("เกม "+this.gameStore.get(i).getTitle()+" ราคา "+this.gameStore.get(i).getNormalprice()+" ");
                     }
                    return status;
                }

            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            
        }
        System.out.println("not found the game to add");
        return null;
    }
    

    public GameStatus changeGameStatusFrom(AdminAccount admin, String title, GameStatus status,double discountpercent) {
        try {
            for (int i = 0; i < this.gameStore.size(); i++) {
                if (this.gameStore.get(i).getTitle().equals(title)) {
                    this.gameStore.get(i).setStatus(status);
                     if(status==GameStatus.DISCOUNTED){
                         this.gameStore.get(i).setPrice((this.gameStore.get(i).getSpecialPrice()*discountpercent)/100);
                         System.out.print("ลดราคาแล้ว "+this.gameStore.get(i).getTitle()+" ในร้านค้า "+this+" เหลือ "+this.gameStore.get(i).getSpecialPrice()+" ");
                     }
                    return status;
                }

            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            
        }
        System.out.println("not found the game to add");
        return null;
    }
    
//    public void calculateDiscount(Game game,double discountpercent){
//        if(this.changeGameStatusFrom(admin, name, gamestatus)==GameStatus.DISCOUNTED){
//            game.setPrice((game.getPrice()*discountpercent)/100);
//        }
//    }

    @Override
    public boolean removeGame(AdminAccount admin, Game game) {
        try {
            if (game == null) {
                System.out.println("Game is Not Null");
                return false;
            }
            for (int i = 0; i < this.gameStore.size(); i++) {
                if (this.gameStore.get(i).equals(game)) {
                    this.gameStore.remove(game);
                    System.out.println("Successfully Removed");
                    return true;
                }

            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        System.out.println("Not found game to remove");
        return false;
    }

    @Override
    public String toString() {
        return name;
    }



}
