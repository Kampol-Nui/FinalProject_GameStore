package service;

//import io.AllCustomer;
import person.Person;
import account.Account;
import admin.io.ReadWritePurchaseHistoryTranscription;

//admin.writePurchaseHistory(cus2);
//admin.readPurchaseHistory("purchasehistory.dat",cus2);
public class AdminAccount extends Account {

    public AdminAccount(String username, String password, Person person) {
        super(username, password, person);

    }
    
    
 
    
//    public void adminReadPurchaseHistoryOf(CustomerAccount ac){
//      ReadWritePurchaseHistoryTranscription.readPurchaseHistory(ac);
//    }
//    public void readCustomer(AdminAccount ad, String filename) {
//        try (FileInputStream fis = new FileInputStream(filename);
//                BufferedInputStream bis = new BufferedInputStream(fis);
//                DataInputStream dis = new DataInputStream(bis)) {
//
//            //cus = new CustomerAccount(dis.readDouble(),dis.readUTF(),dis.readUTF(),dis.readDouble());
////            while(dis.available() > 0){
////                double d = dis.readDouble();
////                String n = dis.readUTF();
////                String p = dis.readUTF();
////                double m = dis.readDouble();
////                System.out.println("Cus_ID = "+d+"  Cus_Username = "+n+"  Cus_Password = "+p+"  Cus_Money = "+m);
////            //System.out.println("Cus_ID = "+dis.readDouble()+"Cus_Username = "+dis.readUTF()+"Cus_Password = "+dis.readUTF()+"Cus_Money = "+dis.readDouble());
////            }
//            int numOfmember = dis.readInt();
//            AllCustomer[] readCus = new AllCustomer[numOfmember];
//            for (int i = 0; i < numOfmember; i++) {
//                readCus[i] = new AllCustomer(dis.readDouble(), dis.readUTF(), dis.readUTF(), dis.readDouble());
//                System.out.println(readCus[i]);
//
//            }
////            for (int i = 0; i <= dis.readInt(); i++) {
////                
////            System.out.println(dis.readDouble());
////            System.out.println(dis.readUTF());
////            System.out.println(dis.readUTF());
////            System.out.println(dis.readDouble());
////                System.out.println(dis.readDouble());
////                System.out.println(dis.readUTF());
////            System.out.println(dis.readUTF());
////            System.out.println(dis.readDouble());
////            }
////                dis.readInt();
////            System.out.println(dis.readDouble());
////            System.out.println(dis.readUTF());
////            System.out.println(dis.readUTF());
////            System.out.println(dis.readDouble());}
//        } catch (FileNotFoundException ex) {
//            ex.getMessage();
//        } catch (IOException ex) {
//            ex.getMessage();
//        }
//    }
//
//    public void WriteCustomerData(AdminAccount admin, AllCustomer allcus) {
//        try (FileOutputStream fos = new FileOutputStream("file" + "_" + ".dat");
//                BufferedOutputStream bos = new BufferedOutputStream(fos);
//                DataOutputStream dout = new DataOutputStream(bos)) {
//            int numOfcus = allcus.getCus().length;
//            //CustomerAccount [] cus = allcus.getCus();
//            dout.writeInt(numOfcus);
//            for (int i = 0; i < numOfcus; i++) {
//                dout.writeDouble(allcus.getCus(i).getUniqueId());
//                dout.writeUTF(allcus.getCus(i).getUsername());
//                dout.writeUTF(allcus.getCus(i).getPassword());
//                dout.writeDouble(allcus.getCus(i).getMyMoney());
//
//            }
//
//        } catch (FileNotFoundException ex) {
//            ex.getMessage();
//        } catch (IOException ex) {
//            ex.getMessage();
//        }
//
//    }
    
//    public void writePurchaseHistory(CustomerAccount ac){
//        try(Connection con = DBconnection.getConnecting();
//            Statement stm = con.createStatement();
//            FileOutputStream fos = new FileOutputStream("Purchasehistory.dat");
//            BufferedOutputStream bos = new BufferedOutputStream(fos);
//            FileWriter fwt = new FileWriter("Purchasehistory.txt");
//            BufferedWriter out = new BufferedWriter(fwt);
//            DataOutputStream dos = new DataOutputStream(fos)){
//            ResultSet rs = null;
//            rs = stm.executeQuery("SELECT * FROM PURCHASEHISTORY WHERE id=" + ac.getUniqueId());
//            while(rs.next()){
//                String timestamp = rs.getString("TIMESTAMP");
//                long id = rs.getLong("ID");
//                String username = rs.getString("USERNAME");
//                String game = rs.getString("GAME");
//                double totalprice = rs.getDouble("TOTALPRICE");
//                double mymoney = rs.getDouble("MYMONEY");
//                dos.writeUTF(timestamp);
//                dos.writeLong(id);
//                dos.writeUTF(username);
//                dos.writeUTF(game);
//                dos.writeDouble(totalprice);
//                dos.writeDouble(mymoney);
//                out.write(timestamp+" " + id+" " + username+" " + game+" " + totalprice+" " + mymoney + "\n");
//            }
//        
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex.getMessage());
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    
//    }
    
//    public void readPurchaseHistory(String filename,CustomerAccount ac){
//        try (FileInputStream fis = new FileInputStream(filename);
//                BufferedInputStream bis = new BufferedInputStream(fis);
//                FileReader frd = new FileReader("Purchasehistory.txt");
//                BufferedReader rdr = new BufferedReader(frd);
//                DataInputStream dis = new DataInputStream(bis)
//                ) {
//            //readcus[i] = new 
//            //rdr.read()
//            String line;
//                while ((line = rdr.readLine()) != null) {
//                System.out.println(line);
//            }
////            for (int i = 0; i < dataaccess.DBmanager.SelectOrderNumber(ac); i++) {
////                
////                System.out.println(dis.readUTF()+" "+dis.readLong()+" "+dis.readUTF()+" "+dis.readUTF()+" "+dis.readDouble()+" "+dis.readDouble());
////                
////            }
//        } catch (FileNotFoundException ex) {
//            System.out.println(ex.getMessage());
//        }   catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }

}
    

      
