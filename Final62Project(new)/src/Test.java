
//import io.AllCustomer;
import service.AdminAccount;

import account.AccountStatus;
import admin.io.ReadWritePurchaseHistoryTranscription;
import dataaccess.DBmanager;
import service.GameStore;
import game.Game;
import game.GameStatus;
import java.util.Scanner;
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
    //static GameStore g1 = new GameStore("EPRIC");

    static CustomerAccount customer = new CustomerAccount(null, null, AccountStatus.ACTIVE);
    static int choice;
    static String mainMenu = "Menu:\n"
            + "1. Register\n"
            + "2. Login\n"
            + "0. exit\n"
            + "Select menu: ";
    static String customerMenu = "Menu:\n"
            + "1. Topup Money\n"
            + "2. List Game in Store\n"
            + "3. Add Game To Cart\n"
            + "4. Check Money\n"
            + "0. exit\n"
            + "Select menu: ";
    static Scanner input = new Scanner(System.in);

    public static void mainMenu() {

        do {
            System.out.print(mainMenu);
            choice = input.nextInt();

            switch (choice) {
                case 1: {
//                    String phone = null;
//                    String email = null;
//                    String name = null;
                    String username = null;
                    String password = null;
//                    System.out.print("Input your name: ");
//                    name = input.next();
//                    System.out.print("Input your email: ");
//                    email = input.next();
//                    System.out.print("Input your phone: ");
//                    phone = input.next();
                    System.out.print("Input your username: ");
                    username = input.next();
                    System.out.print("Input your password: ");
                    password = input.next();
                    System.out.println("Registered\n");
                    customer = new CustomerAccount(username, password, AccountStatus.ACTIVE);
                    customer.genID();
                    //customerAccount.callKeepCustomerInfo();
                    break;
                }
                case 2: {

                    String username = null;
                    String password = null;
                    System.out.print("Input your username: ");
                    username = input.next();
                    System.out.print("Input your password: ");
                    password = input.next();
                    if (DBmanager.loginCustomer(username, password) == false) {
                        System.out.println("Wrong Username or Password");

                    } else {
                        //customerMenu(DBmanager.getObjectCustomerFrom(username, password));
                        customer = DBmanager.getObjectCustomerFrom(username, password);
                        return;
                        //customerMenu(customerAccount);
                    }
                    break;
                }
                case 0: {
                    break;
                }

            }
        } while (choice != 0);

    }

    public static void main(String[] args) {
//       dataaccess.DBmanager.CreateTable();

        mainMenu();

//   do {
//            System.out.print(mainMenu);
//            choice = input.nextInt();
//
//            switch (choice) {
//                case 1: {
//                    String phone = null;
//                    String email = null;
//                    String name = null;
//                    String username = null;
//                    String password = null;
//                    System.out.print("Input your name: ");
//                    name = input.next();
//                    System.out.print("Input your email: ");
//                    email = input.next();
//                    System.out.print("Input your phone: ");
//                    phone = input.next();
//                    System.out.print("Input your username: ");
//                    username = input.next();
//                    System.out.print("Input your password: ");
//                    password = input.next();
//                    System.out.println("Registered\n");   
//                    customer = new CustomerAccount(username, password, AccountStatus.ACTIVE);
//                    customer.genID();
//                    //customerAccount.callKeepCustomerInfo();
//                    break;
//                }
//                case 2: {
//                    
//                    String username = null;
//                    String password = null;
//                    System.out.print("Input your username: ");
//                    username = input.next();
//                    System.out.print("Input your password: ");
//                    password = input.next();
//                    if(DBmanager.loginCustomer(username, password)==false){
//                        System.out.println("Wrong Username or Password");
//                    }else{
//                    //customerMenu(DBmanager.getObjectCustomerFrom(username, password));
//                    customer = DBmanager.getObjectCustomerFrom(username, password);
//                    
//                    //customerMenu(customerAccount);
//                    }
//                    break;
//                }
//                case 0: {
//                    break;
//                }
//
//            }
//        } while (choice != 0);
//        CustomerAccount cus1 = new CustomerAccount("Kampol", "kkkkddd", AccountStatus.ACTIVE);
//        CustomerAccount customer = new CustomerAccount("Jiwview", "thisispassword", AccountStatus.ACTIVE);
//        CustomerAccount cus3 = new CustomerAccount("John", "Wick", AccountStatus.ACTIVE);
//        cus1.genID();
//        customer.genID();
        AdminAccount admin = new AdminAccount("NobodyFound", "007");
        AdminAccount admin2 = new AdminAccount("ADM", "123456789");
        
        admin.setCustomerStatusTo(customer, AccountStatus.BLACKLISTED);
        
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

        System.out.println(gameStore.changeGameStatusFrom(admin, "RE7", GameStatus.DISCOUNTED, 50));

        gameStore.listGameFromStore();

        //gameStore.removeGame(admin, game3);
        gameStore.listGameFromStore();

        //gameStore.removeGame(admin, game4);
        gameStore.listGameFromStore();

        System.out.println("=============================TEST FOR CART=========================================");

//        cus1.getMyCart().addGameToCart(gameStore, "RE7");
//        cus1.getMyCart().addGameToCart(gameStore, "RE8");
//        cus1.getMyCart().addGameToCart(gameStore, "RE7");
//       
//        
//        cus1.getMyCart().listGameFromCart();
//        
//        cus1.getMyCart().removeGameFromCart("RE7");
//        cus1.getMyCart().removeGameFromCart("RE7");
//        
//        cus1.getMyCart().listGameFromCart();
        System.out.println("======================================================================");

        customer.getMyCart().addGameToCart(gameStore, "RE7");
        //cus2.getMyCart().addGameToCart(gameStore, "TOMB RIDER");
        //cus2.getMyCart().addGameToCart(gameStore, "RE7");
        //cus2.getMyCart().addGameToCart(gameStore, "RE8");

//        cus1.getMyCart().listGameFromCart();
        System.out.println("======================================================================");

//        cus1.getMyCart().listGameFromCart();
//        
//        System.out.println("=============================TEST FOR BUYING=========================================");
//        
//        cus1.TopupMoney(1000);
        customer.topupMoney(500);
//        
//        cus1.TopupMoney(0);
//        cus1.TopupMoney(-50);
//        
//        cus1.getMyLibrary().addGameFromCartToLibrary();
//        
//        cus1.listBuyingHistory();
//        
//        cus1.getMyLibrary().listMyGameInLibrary(cus1);
        
        customer.getMyLibrary().addGameFromCartToLibrary();

        customer.listBuyingHistory();

        customer.listMyGameInLibrary();

//        customer.getMyLibrary().addGameFromCartToLibrary();
//        
//        customer.getMyLibrary().listMyGameInLibrary(customer);
//        
//        customer.listBuyingHistory();
//        
        customer.customerReadPurchaseHistoryOf();

        admin.seeInfoOfAllCustomer();
    }
}
