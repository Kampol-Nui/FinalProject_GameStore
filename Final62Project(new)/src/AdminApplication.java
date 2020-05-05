
import dataaccess.DBmanager;
import game.Game;
import java.util.Scanner;
import service.AdminAccount;
import service.GameStore;

public class AdminApplication {

    static GameStore g1 = new GameStore("EPRIC");
     
    static int choice;
    static String adminMenu = "Menu:\n"
            + "1. list all customer\n"
            + "2. Add Game To Store\n"
            + "3. Remove Game From Store\n"
            + "4. List Game From Store\n"
            + "0. exit\n"
            + "Select menu: ";
    static String choseMenu = "SelectGame:\n"
            + "1. TOMB RIDER\n"
            + "2. RE7\n"
            + "3. RE8\n"
            + "4. RE9\n"
            + "0. exit\n";
            

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        adminMenu();

    }

    public static void adminMenu() {
        do {
            System.out.print(adminMenu);
            choice = input.nextInt();

            switch (choice) {
                case 1: {
                    DBmanager.seeDataofAllCustomer(ac);
                    break;
                }
                case 2: {

                    AdminAccount admin = new AdminAccount("AdminM", "thisispassword");
                    choseGametoAdd(admin);

                    break;

                }

                case 0: {
                    break;
                }

            }
        } while (choice != 0);

    }

//    public static void choseGametoAdd(AdminAccount admin) {
//     Game game1 = new Game("TOMB RIDER", 20);
//     Game game2 = new Game("RE7", 60);
//     Game game3 = new Game("RE8", 100);
//     Game game4 = new Game("RE9", 40);
//        int chose = 0;
//        do {
//            System.out.println(choseMenu);
//            chose = input.nextInt();
//            switch (chose) {
//
//                case 1:{
//                    g1.addGameFrom(admin, game1);
//                    if(DBmanager.checkRepeatGameName(g1)==false){
//                        System.out.println("เกมในดาต้าเบสซ้ำอะ");
//                    }else{
//                        game1.genID();
//                    }
//                    break;
//                }
//                case 2:{
//                    g1.addGameFrom(admin, game2);
//                    if(DBmanager.checkRepeatGameName(g1)==false){
//                        System.out.println("เกมในดาต้าเบสซ้ำอะ");
//                    }else{
//                        game2.genID();
//                    }
//                    break;
//                }
//                case 3:{
//                    g1.addGameFrom(admin, game3);
//                    if(DBmanager.checkRepeatGameName(g1)==false){
//                        System.out.println("เกมในดาต้าเบสซ้ำอะ");
//                    }else{
//                        game3.genID();
//                    }
//                    break;
//                }
//                case 4:{
//                    g1.addGameFrom(admin, game4);
//                    if(DBmanager.checkRepeatGameName(g1)==false){
//                        System.out.println("เกมในดาต้าเบสซ้ำอะ");
//                    }else{
//                        game4.genID();
//                    }
//                    break;
//                }
//            }
//        } while (chose != 0);
//    }

}
