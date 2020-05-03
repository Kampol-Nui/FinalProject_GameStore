
import dataaccess.DBmanager;
import java.util.Scanner;



public class AdminApplication {

    static int choice;
    static String adminMenu = "Menu:\n"
            + "1. list all customer\n"
            + "0. exit\n"
            + "Select menu: ";

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
                    DBmanager.seeDataofAllCustomer();
                    break;
                }
                case 0: {
                    break;
                }

            }
        } while (choice != 0);

    }

}
