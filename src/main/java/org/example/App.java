package org.example;

import java.io.File;
import java.sql.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Connection con;
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            con= DriverManager.getConnection("jdbc:mysql://103.39.132.102:3306/ah_mahabms","ah_mahabms","Atngtuta9m#o7jLo");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main( String[] args ) throws Exception {


        File f = new File("1-10 list-Solapur.csv");
        // always keep format of the file in csv
        Scanner sc = new Scanner(f);
        LinkedList<String> list = new LinkedList<String>();
        sc.nextLine();
        while(sc.hasNextLine()){
            String applicationNumber = sc.next().split(",")[1];
            list.add(applicationNumber);
            sc.nextLine();
        }
        //System.out.println(list);
        list.forEach(App::setValues);
        //System.out.println(f.exists());

//        String applicationNumber =  "SM6946136547";
//        setValues(applicationNumber);


        System.out.println("document upload enabled");
        con.close();


    }

    private static void setValues(String applicationNumber)  {

        String applicationID = null;
        try {
            applicationID = getApplicationIDByApplicationNumber(applicationNumber);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            getDocUploadPermissionByApplicationID(applicationID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void getDocUploadPermissionByApplicationID(String applicationID) throws SQLException {
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM application_items where application_id = '"+applicationID+"';");
        rs.next();
        // System.out.println(rs.getString("application_item_id"));
        if(rs.getString("doc_upload_permission").equals("0")) {
            String query = "update application_items set doc_upload_permission = '1' where application_id = '" + applicationID + "';";
            int output = con.createStatement().executeUpdate(query);
            //System.out.println(output);

            System.out.println(applicationID);
        }
    }

    public static String getApplicationIDByApplicationNumber(String applicationNumber) throws SQLException {
        ResultSet rs = con.createStatement().executeQuery("select application_id from farmer_application where application_number = '"+applicationNumber+"'");
        rs.next();
        return rs.getString(1);
    }
}
