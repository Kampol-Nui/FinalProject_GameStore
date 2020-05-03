/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.io;

import dataaccess.DBconnection;
import dataaccess.DBmanager;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.AdminAccount;
import service.CustomerAccount;

/**
 *
 * @author MINI
 */
public class ReadWritePurchaseHistoryTranscription {

    public static void writePurchaseHistory(CustomerAccount ac) {
        try {
            FileOutputStream fos = new FileOutputStream("Purchasehistory.dat");
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            FileWriter fwt = new FileWriter("Purchasehistory.txt");

            BufferedWriter out = new BufferedWriter(fwt);
            DataOutputStream dos = new DataOutputStream(fos);

            try (Connection con = DBconnection.getConnecting();
                    Statement stm = con.createStatement();) {
                ResultSet rs = null;
                rs = stm.executeQuery("SELECT * FROM PURCHASEHISTORY WHERE id=" + ac.getUniqueId());
                while (rs.next()) {
                    String timestamp = rs.getString("TIMESTAMP");
                    long id = rs.getLong("ID");
                    String username = rs.getString("USERNAME");
                    String game = rs.getString("GAME");
                    double totalprice = rs.getDouble("TOTALPRICE");
                    double mymoney = rs.getDouble("MYMONEY");
                    dos.writeUTF(timestamp);
                    dos.writeLong(id);
                    dos.writeUTF(username);
                    dos.writeUTF(game);
                    dos.writeDouble(totalprice);
                    dos.writeDouble(mymoney);
                    out.write(timestamp + " " + id + " " + username + " " + game + " " + totalprice + " " + mymoney + "\n");
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBmanager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(AdminAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void readPurchaseHistory(String filename, CustomerAccount ac) {
        try (FileInputStream fis = new FileInputStream(filename);
                BufferedInputStream bis = new BufferedInputStream(fis);
                FileReader frd = new FileReader("Purchasehistory.txt");
                BufferedReader rdr = new BufferedReader(frd);
                DataInputStream dis = new DataInputStream(bis)) {
            String line;
            while ((line = rdr.readLine()) != null) {
                System.out.println(line);
            }
//            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}