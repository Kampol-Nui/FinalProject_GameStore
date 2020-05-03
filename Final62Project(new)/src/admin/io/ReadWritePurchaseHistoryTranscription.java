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
        try (Connection con = DBconnection.getConnecting();
                Statement stm = con.createStatement();
                
                FileWriter fwt = new FileWriter("Purchasehistory"+"_"+ac.getUsername()+".txt");
                BufferedWriter out = new BufferedWriter(fwt)
                ) {
            ResultSet rs = null;
            rs = stm.executeQuery("SELECT * FROM PURCHASEHISTORY WHERE id=" + ac.getUniqueId());
            while (rs.next()) {
                String timestamp = rs.getString("TIMESTAMP");
                long id = rs.getLong("ID");
                String username = rs.getString("USERNAME");
                String game = rs.getString("GAME");
                double totalprice = rs.getDouble("TOTALPRICE");
                double mymoney = rs.getDouble("MYMONEY");
                
                out.write(timestamp + " " + id + " " + username + " " + game + " " + totalprice + " " + mymoney + "\n");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void readPurchaseHistory(CustomerAccount ac) {
        try (
                FileReader frd = new FileReader("Purchasehistory"+"_"+ac.getUsername()+".txt");
                BufferedReader rdr = new BufferedReader(frd)
                ) {
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
