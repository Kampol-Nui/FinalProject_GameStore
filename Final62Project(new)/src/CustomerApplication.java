
import account.AccountStatus;
import dataaccess.DBmanager;

import java.util.Scanner;
import person.Person;
import service.CustomerAccount;
import service.GameStore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MINI
 */
public class CustomerApplication {
    static GameStore g1 = new GameStore("EPRIC");
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

    public static void main(String[] args) {
        
        mainMenu();
    }

    public static void mainMenu() {
       
        do {
            System.out.print(mainMenu);
            choice = input.nextInt();

            switch (choice) {
                case 1: {
                    String phone = null;
                    String email = null;
                    String name = null;
                    String username = null;
                    String password = null;
                    System.out.print("Input your name: ");
                    name = input.next();
                    System.out.print("Input your email: ");
                    email = input.next();
                    System.out.print("Input your phone: ");
                    phone = input.next();
                    System.out.print("Input your username: ");
                    username = input.next();
                    System.out.print("Input your password: ");
                    password = input.next();
                    System.out.println("Registered\n");   
                    CustomerAccount customerAccount = new CustomerAccount(username, password, AccountStatus.ACTIVE);
                    customerAccount.genID();
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
                    if(DBmanager.loginCustomer(username, password)==false){
                        System.out.println("Wrong Username or Password");
                    }else{
                    //customerMenu(DBmanager.getObjectCustomerFrom(username, password));
                    CustomerAccount customerAccount = new CustomerAccount(username, password, AccountStatus.ACTIVE);
                    customerMenu(customerAccount);}
                    break;
                }
                case 0: {
                    break;
                }

            }
        } while (choice != 0);

    }

    public static void customerMenu(CustomerAccount customerAccount) {
        do {
            System.out.print(customerMenu);
            choice = input.nextInt();

            switch (choice) {
                case 1: {
                    double money = 0;
                    System.out.print("Input your money: ");
                    money = input.nextDouble();
                    customerAccount.TopupMoney(money);
                    System.out.println(customerAccount.getUsername());
                    break;
                }
                case 2: {
                    g1.listGameInStore();
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    System.out.println(customerAccount.getMyMoney());
                    break;
                }
                case 0: {
                    break;
                }

            }
        } while (choice != 0);

    }
}
