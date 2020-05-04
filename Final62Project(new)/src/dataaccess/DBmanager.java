package dataaccess;

import account.AccountStatus;
import game.Game;

import genarate.TimeStamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.AdminAccount;
import service.CustomerAccount;
import service.GameStore;


public class DBmanager {

    public static void keepCustomerInfo(CustomerAccount ac) {
        String sql1 = "INSERT INTO CUSTOMERACCOUNT " + "(id,username,password,mymoney)" + "VALUES(?,?,?,?)";
        String sql2 = "INSERT INTO PERSONINFO " + "(name,email,phone)" + "VALUES(?,?,?)";
        try (Connection conn = DBconnection.getConnecting();) {
            try (PreparedStatement pstm = conn.prepareStatement(sql1);) {
                pstm.setDouble(1, ac.getUniqueId());
                pstm.setString(2, ac.getUsername());
                pstm.setString(3, ac.getPassword());
                pstm.setDouble(4, ac.getMyMoney());
                pstm.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    public static void keepGameInfo(Game game) {
        String sql1 = "INSERT INTO GAMEINSTORE " + "(id,title,specialprice,normalprice,status)" + "VALUES(?,?,?,?,?)";
        
        try (Connection conn = DBconnection.getConnecting();) {
            try (PreparedStatement pstm = conn.prepareStatement(sql1);) {
                pstm.setLong(1, game.getId());
                pstm.setString(2, game.getTitle());
                pstm.setDouble(3, game.getSpecialPrice());
                pstm.setDouble(4, game.getNormalprice());
                pstm.setString(5, game.getStatus().name());
                pstm.executeUpdate();
            } catch (SQLException ex) {
                System.out.println(ex);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void topupMoney(CustomerAccount ac) {
        double lastMoney;
        String sql1 = "INSERT INTO TOPUPBIll " + "(timestamp,id,username,topup,topupstatus)" + "VALUES(?,?,?,?,?)";
        //String sql2 = "UPDATE CUSTOMERACCOUNT set mymoney=" + ac.getMyMoney() + " WHERE id =" + ac.getUniqueId();
        try (Connection con = DBconnection.getConnecting();) {
            try (
                    PreparedStatement stm = con.prepareStatement(sql1);) {
                stm.setString(1, new TimeStamp().toString());
                stm.setLong(2, ac.getUniqueId());
                stm.setString(3, ac.getUsername());
                stm.setDouble(4, ac.getTopupMoney());
               
                stm.executeUpdate();
                lastMoney = ac.getTopupMoney() + SelectLastMoney(ac);
                String sql2 = "UPDATE CUSTOMERACCOUNT set MYMONEY=" + lastMoney + " WHERE id =" + ac.getUniqueId();
                try (Statement stmm = con.createStatement();) {
                    stmm.executeUpdate(sql2);
                    System.out.println("เติมเงินเสร็จสมบูรณ์");
                    System.out.println(lastMoney);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public static void SelectTablePurchaseHistory(CustomerAccount ac) {
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;
            String query = ("SELECT * FROM PURCHASEHISTORY WHERE ID =" + ac.getUniqueId());
            rs = stm.executeQuery(query);
            System.out.println("================================================================================================================");
            System.out.println(String.format("%20s %s %10s %s %20s %s %20s %s %14s %s %s", "DATETIME", "|", "ID", "|", "USERNAME", "|", "GAME", "|", "TOTALPRICE", "|", "MYMONEY"));
            System.out.println("================================================================================================================");
            while (rs.next()) {
                String dateTime = rs.getString("timestamp");
                long id = rs.getLong("id");
                String username = rs.getString("username");
                String game = rs.getString("game");
                double totalprice = rs.getDouble("totalprice");
                double mymoney = rs.getDouble("mymoney");
                System.out.println(String.format("%20s %s %10s %s %20s %s %20s %s %14s %s %s", dateTime, "|", id, "|", username, "|", game, "|", totalprice, "|", mymoney));
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------");

        } catch (SQLException ex) {
            System.out.println(ex);;
        }
    }

    public static void PurchaseGame(CustomerAccount ac) {
        String sql1 = "INSERT INTO PURCHASEHISTORY " + "(timestamp,id,username,game,totalprice,mymoney)" + "VALUES(?,?,?,?,?,?)";
        try (Connection con = DBconnection.getConnecting();) {
            try (
                    PreparedStatement pstm = con.prepareStatement(sql1);) {
                for (int i = 0; i < ac.getMyCart().getItemInCart().size(); i++) {
                    double myEachMoney = DBmanager.SelectLastMoney(ac) - ac.getMyCart().getEachGamePrice(i);
                    String sql3 = "UPDATE CUSTOMERACCOUNT set MYMONEY=" + myEachMoney + " WHERE id =" + ac.getUniqueId();
                    try (Statement stm = con.createStatement();) {

                        stm.executeUpdate(sql3);
                    } catch (SQLException ex) {

                    }
                    pstm.setString(1, new TimeStamp().toString());
                    pstm.setDouble(2, ac.getUniqueId());
                    pstm.setString(3, ac.getUsername());
                    pstm.setString(4, ac.getMyCart().getItemInCart().get(i).getTitle());
                    pstm.setDouble(5, ac.getMyCart().getEachGamePrice(i));
                    pstm.setDouble(6, myEachMoney);

                    pstm.executeUpdate();

                }

            } catch (SQLException ex) {
                ex.getMessage();
            }
            String sql2 = "UPDATE CUSTOMERACCOUNT set MYMONEY=" + ac.getMyMoney() + " WHERE id =" + ac.getUniqueId();
            try (Statement stm = con.createStatement();) {
                stm.executeUpdate(sql2);
                System.out.println("ชำระเงินเสร็จสมบูรณ์ โปรดตรวจสอบ Library ของคุณหลังชำระเงิน ");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        DBmanager.addGametoDatabase(ac);

    }

    public static double SelectLastMoney(CustomerAccount ac) {
        double money = 0;
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;

            //String query = ("SELECT * FROM CUSTOMERACCOUNT C1 WHERE C1.ORDER_NUMBER=(SELECT MAX(ORDER_NUMBER) FROM CUSTOMERACCOUNT C2 WHERE C1.ID = C2.ID) AND id=" + ac.getUniqueId());
            String query2 = "SELECT * FROM CUSTOMERACCOUNT WHERE ID=" + ac.getUniqueId();
            rs = stm.executeQuery(query2);

            if (rs.next()) {

                money = rs.getDouble("MYMONEY");

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return money;
    }

    public static void CreateTable() {
        try (Connection con = dataaccess.DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            try {
                stm.executeUpdate("DROP TABLE CUSTOMERACCOUNT");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("DROP TABLE TOPUPBILL");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("DROP TABLE PURCHASEHISTORY");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("DROP TABLE LIBRARY");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("DROP TABLE GAMEINSTORE");
            } catch (SQLException ex) {
            }
            //try {stm.executeUpdate("CREATE TABLE customer (cus_id INT NOT NULL, cus_name VARCHAR(100),PRIMARY KEY (cus_id))");} catch (SQLException ex) {} 
            try {
                stm.executeUpdate("CREATE TABLE CUSTOMERACCOUNT (ID BIGINT not null primary key,USERNAME VARCHAR(50),PASSWORD VARCHAR(50),MYMONEY DOUBLE)");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("CREATE TABLE TOPUPBILL (ORDER_NUMBER INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),"
                        + " TIMESTAMP VARCHAR(50),ID BIGINT,USERNAME VARCHAR(50),TOPUP DOUBLE)");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("CREATE TABLE PURCHASEHISTORY (ORDER_NUMBER INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),"
                        + " TIMESTAMP VARCHAR(50),ID BIGINT,USERNAME VARCHAR(50),GAME VARCHAR(50),TOTALPRICE DOUBLE,MYMONEY DOUBLE)");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("CREATE TABLE LIBRARY (ORDER_NUMBER INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1,INCREMENT BY 1),"
                        + " ID BIGINT,GAME VARCHAR(50))");
            } catch (SQLException ex) {
            }
            try {
                stm.executeUpdate("CREATE TABLE GAMEINSTORE "
                        + " (ID VARCHAR(50),TITLE VARCHAR(50),SPECIALPRICE DOUBLE,NORMALPRICE DOUBLE,STATUS VARCHAR(50))");
            } catch (SQLException ex) {
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void seeDataofAllCustomer(AdminAccount ac) {
        
        long id = 0;
        String username = null;
        String password = null;
        double myMoney = 0;
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;

            String query = "SELECT * FROM CUSTOMERACCOUNT";
            rs = stm.executeQuery(query);
            System.out.println("========================================================================");
            System.out.println(String.format("%10s %s %20s %s %20s %s %10s ", "ID", "|", "USERNAME", "|", "PASSWORD", "|", "MYMONEY"));
            System.out.println("========================================================================");
            while (rs.next()) {
                id = rs.getLong("ID");
                username = rs.getString("USERNAME");
                password = rs.getString("PASSWORD");
                myMoney = rs.getDouble("MYMONEY");
                System.out.println(String.format("%10s %s %20s %s %20s %s %10s ", id, "|", username, "|", password, "|", myMoney));
                System.out.println("------------------------------------------------------------------------");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static boolean verifyCustomer(String username, String password) {
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;

            String query = "SELECT * FROM CUSTOMERACCOUNT";
            rs = stm.executeQuery(query);

            while (rs.next()) {
                String DBusername = rs.getString("USERNAME");
                String DBpassword = rs.getString("PASSWORD");
                if (username.equals(DBusername) && password.equals(DBpassword)) {
                    return true;
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public static long incrementLastCustomerID() {
        long id = 1000000000;
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;

            String query = ("SELECT MAX(ID) AS MAXID FROM CUSTOMERACCOUNT");
            
            rs = stm.executeQuery(query);

            if (rs.next()) {
                id = rs.getLong(1);
                id++;
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }
    
    public static long incrementLastGameID() {
        long id = 2000000000;
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;

            String query = ("SELECT MAX(ID) AS MAXID FROM GAMEINSTORE");
            
            rs = stm.executeQuery(query);

            if (rs.next()) {
                id = rs.getLong(1);
                id++;
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id;
    }

    public static int SelectOrderNumber(CustomerAccount ac) {
        int ordernumber = 0;
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;

            //String query = ("SELECT * FROM PURCHASEHISTORY C1 WHERE C1.ORDER_NUMBER=(SELECT MAX(ORDER_NUMBER) FROM PURCHASEHISTORY C2 WHERE C1.ID = C2.ID) AND id=" + ac.getUniqueId());
            String query2 = "SELECT COUNT(*) FROM PURCHASEHISTORY WHERE ID=" + ac.getUniqueId();
            rs = stm.executeQuery(query2);

            if (rs.next()) {

                ordernumber = rs.getInt(1);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ordernumber;
    }

    public static void addGametoDatabase(CustomerAccount ac) {
        String sql1 = "INSERT INTO LIBRARY " + "(id,game)" + "VALUES(?,?)";
        try (Connection con = DBconnection.getConnecting();) {
            try (
                    PreparedStatement pstm = con.prepareStatement(sql1);) {
                for (int i = 0; i < ac.getMyCart().getItemInCart().size(); i++) {
//                    double myEachMoney = DBmanager.SelectLastMoney(ac) - ac.getMyCart().getEachGamePrice(i);
//                    String sql3 = "UPDATE CUSTOMERACCOUNT set MYMONEY=" + myEachMoney + " WHERE id =" + ac.getUniqueId();
//                    try (Statement stm = con.createStatement();) {
//
//                        stm.executeUpdate(sql3);
//                    } catch (SQLException ex) {
//
//                    }
                    pstm.setDouble(1, ac.getUniqueId());
                    pstm.setString(2, ac.getMyCart().getItemInCart().get(i).getTitle());

                    pstm.executeUpdate();

                }

            } catch (SQLException ex) {
                ex.getMessage();
            }
            String sql2 = "UPDATE CUSTOMERACCOUNT set MYMONEY=" + ac.getMyMoney() + " WHERE id =" + ac.getUniqueId();
            try (Statement stm = con.createStatement();) {
                stm.executeUpdate(sql2);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static CustomerAccount getObjectCustomerFrom(String username1, String password1) {
        long id = 0;
        String username = null;
        String password = null;
        double myMoney = 0;
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;

            String query = "SELECT * FROM CUSTOMERACCOUNT WHERE USERNAME= '" + username1 + "'AND PASSWORD='" + password1 + "'";
            rs = stm.executeQuery(query);
            while (rs.next()) {
                id = rs.getLong("ID");
                username = rs.getString("USERNAME");
                password = rs.getString("PASSWORD");
                myMoney = rs.getDouble("MYMONEY");

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return new CustomerAccount(username,password,AccountStatus.ACTIVE);
    }

    public static boolean checkRepeatGameInLibrary(CustomerAccount ac) {

        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;
            if (ac.getMyCart().getItemInCart().isEmpty()) {
                return false;
            }
            for (int i = 0; i < ac.getMyCart().getItemInCart().size(); i++) {
                String query = "SELECT * FROM LIBRARY WHERE ID= " + ac.getUniqueId() + "AND GAME='" + ac.getMyCart().getItemInCart().get(i).getTitle() + "'";
                rs = stm.executeQuery(query);

                while (rs.next()) {
                    String gameName = rs.getString("GAME");
                    if (gameName.equals(ac.getMyCart().getItemInCart().get(i).getTitle())) {
                        return false;
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public static boolean checkRepeatName(CustomerAccount ac) {

        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;
            for (int i = 0; i < ac.getMyCart().getItemInCart().size(); i++) {
                String query = "SELECT * FROM LIBRARY WHERE USERNAME='" + ac.getUsername() + "'";
                rs = stm.executeQuery(query);

                while (rs.next()) {
                    String gameName = rs.getString("GAME");
                    if (gameName.equals(ac.getMyCart().getItemInCart().get(i).getTitle())) {
                        return false;
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }
    
    public static boolean checkRepeatGameName(GameStore store) {

        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();) {
            ResultSet rs = null;
            
                String query = "SELECT * FROM GAMEINSTORE ";
                rs = stm.executeQuery(query);
            for (int i = 0; i < store.getGames().size(); i++) {
                 while (rs.next()) {
                    String gameName = rs.getString("TITLE");
                    if (gameName.equals(store.getGames().get(i).getTitle())) {
                        return false;
                    }
                }
                
            }
               
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    public static void listRecentLibrary(CustomerAccount ac) {
        String sql1 = "SELECT * FROM LIBRARY WHERE ID=" + ac.getUniqueId();
        try (Connection con = DBconnection.getConnecting();) {
            try (
                    Statement stm = con.createStatement()) {

                ResultSet rs = null;
//                    double myEachMoney = DBmanager.SelectLastMoney(ac) - ac.getMyCart().getEachGamePrice(i);
//                    String sql3 = "UPDATE CUSTOMERACCOUNT set MYMONEY=" + myEachMoney + " WHERE id =" + ac.getUniqueId();
//                    try (Statement stm = con.createStatement();) {
//
//                        stm.executeUpdate(sql3);
//                    } catch (SQLException ex) {
//                  
//                    }
                rs = stm.executeQuery(sql1);
                while (rs.next()) {

                    String game = rs.getString("GAME");

                    System.out.println(game);

                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public static void listRecentGameInStore() {
        String sql1 = "SELECT * FROM GAMEINSTORE" ;
        try (Connection con = DBconnection.getConnecting();) {
            try (
                    Statement stm = con.createStatement()) {

                ResultSet rs = null;
//                    double myEachMoney = DBmanager.SelectLastMoney(ac) - ac.getMyCart().getEachGamePrice(i);
//                    String sql3 = "UPDATE CUSTOMERACCOUNT set MYMONEY=" + myEachMoney + " WHERE id =" + ac.getUniqueId();
//                    try (Statement stm = con.createStatement();) {
//
//                        stm.executeUpdate(sql3);
//                    } catch (SQLException ex) {
//                  
//                    }
                rs = stm.executeQuery(sql1);
                while (rs.next()) {

                    long id = rs.getLong("ID");
                    String title = rs.getString("TITLE");
                    double specialprice = rs.getDouble("SPECIALPRICE");
                    //double normalprice = rs.getDouble("NORMALPRICE");
                    System.out.println(id+" "+title+" "+specialprice);

                }

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
