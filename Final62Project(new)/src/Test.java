
//import io.AllCustomer;
import service.AdminAccount;
import account.AccountStatus;
import service.GameStore;
import game.Game;
import game.GameStatus;
import service.CustomerAccount;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MINI
 */
public class Test {

    public static void main(String[] args) {
//       dataaccess.DBmanager.CreateTable();
        
        

        
        CustomerAccount cus1 = new CustomerAccount("Kampol", "kkkkddd", AccountStatus.ACTIVE);
        CustomerAccount cus2 = new CustomerAccount("Jiwview", "thisispassword", AccountStatus.ACTIVE);
        CustomerAccount cus3 = new CustomerAccount("John", "Wick", AccountStatus.ACTIVE);
        
        AdminAccount admin = new AdminAccount("NobodyFound", "007");
        AdminAccount admin2 = new AdminAccount("ADM", "123456789");
        
        
        GameStore gameStore = new GameStore("EPRICK");
       
        
        
        Game game1 = new Game("TOMB RIDER", 20);
        Game game2 = new Game("RE7", 60);
        Game game3 = new Game("RE8", 100);
        Game game4 = new Game("RE9", 40);

        System.out.println("=============================TEST FOR GAMESTORE=========================================");

        gameStore.addGameFrom(admin, game1);
        gameStore.addGameFrom(admin, game2);
        gameStore.addGameFrom(admin, game3);
        gameStore.addGameFrom(admin, game4);
        
        System.out.println(gameStore.changeGameStatusFrom(admin, "RE7", GameStatus.DISCOUNTED,50));
        
        gameStore.listGameFromStore();
        
        gameStore.removeGame(admin, game3);
        
        gameStore.listGameFromStore();
        
        gameStore.removeGame(admin, game4);
        
        gameStore.listGameFromStore();
        
        System.out.println("=============================TEST FOR CART=========================================");
        
        cus1.getMyCart().addGameToCart(gameStore, "RE7");
        cus1.getMyCart().addGameToCart(gameStore, "RE8");
        cus1.getMyCart().addGameToCart(gameStore, "RE7");
       
        
        cus1.getMyCart().listGameFromCart();
        
        cus1.getMyCart().removeGameFromCart("RE7");
        cus1.getMyCart().removeGameFromCart("RE7");
        
        cus1.getMyCart().listGameFromCart();
        
        System.out.println("======================================================================");
        
        cus2.getMyCart().addGameToCart(gameStore, "RE7");       
        cus2.getMyCart().addGameToCart(gameStore, "TOMB RIDER");
        cus2.getMyCart().addGameToCart(gameStore, "RE7");
        cus2.getMyCart().addGameToCart(gameStore, "RE8");
        
        cus1.getMyCart().listGameFromCart();
        
        System.out.println("======================================================================");
            
        cus1.getMyCart().listGameFromCart();
        
        System.out.println("=============================TEST FOR BUYING=========================================");
        
        cus1.TopupMoney(1000);
        cus2.TopupMoney(500);
        
        cus1.TopupMoney(0);
        cus1.TopupMoney(-50);
        
        cus1.getMyLibrary().addGameFromCartToLibrary();
        
        cus1.listBuyingHistory();
        
        cus1.getMyLibrary().listMyGameInLibrary(cus1);
        
        cus2.getMyLibrary().addGameFromCartToLibrary();
        
        cus2.listBuyingHistory();
        
        cus2.getMyLibrary().listMyGameInLibrary(cus2);
        
        cus2.getMyLibrary().addGameFromCartToLibrary();
        
        cus2.getMyLibrary().listMyGameInLibrary(cus2);
        
        cus2.listBuyingHistory();
        
        cus2.customerReadPurchaseHistoryOf();
        
        admin.seeInfoOfAllCustomer();
    }    
}


