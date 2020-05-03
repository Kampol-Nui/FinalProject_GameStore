
//import io.AllCustomer;
import service.AdminAccount;
import person.Person;
import account.AccountStatus;
import admin.io.ReadWritePurchaseHistoryTranscription;
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
        
        

        Person person = new Person("Kumpol", "kumpol@gmail.com", "0945481112");
        Person person2 = new Person("Jiwview", "jiwview@gmail.com", "16545416");
        Person person3 = new Person("John", "wick@gmail.com", "083746800");
        Person person4 = new Person("Sarah", "adsarah@gmail.com", "058794612");
        Person person5 = new Person("Micheal", "admicheal@gmail.com", "0946781415");
        
        CustomerAccount cus1 = new CustomerAccount("Kampol", "kkkkddd", AccountStatus.ACTIVE, person);
        CustomerAccount cus2 = new CustomerAccount("Jiwview", "thisispassword", AccountStatus.ACTIVE, person2);
        CustomerAccount cus3 = new CustomerAccount("John", "Wick", AccountStatus.ACTIVE, person3);
        
        AdminAccount admin = new AdminAccount("NobodyFound", "007", person4);
        AdminAccount admin2 = new AdminAccount("ADM", "123456789", person5);
        
        
        GameStore gameStore = new GameStore("EPRICK");
       
        
        
        Game game1 = new Game("01", "TOMB RIDER", 20);
        Game game2 = new Game("02", "RE7", 60);
        Game game3 = new Game("03", "RE8", 100);
        Game game4 = new Game("04", "RE9", 40);

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
        
        cus1.getMyCart().removeGameFromCart(cus1, "RE7");
        cus1.getMyCart().removeGameFromCart(cus1, "RE7");
        
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
    }    
}


