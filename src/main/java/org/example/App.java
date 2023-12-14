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

    public static void main(String[] args) throws Exception {
        try {
            System.out.println("hello");
            String[] files = {
                    "1-10 list-Vashim.csv",
                    "1-10 list-Wardha.csv",
                    "1-10 list-Beed.csv",
                    "1-10 list-Bhandara.csv",
                    "1-10 list-Buldhana.csv",
                    "1-10 list-Jalna.csv",
                    "1-10 list-nagpur.csv",
                    "1-10 list-nanded.csv",
                    "1-10 list-nandurbar.csv",
                    "1-10 list-palghar.csv",
                    "1-10 list-parbhani.csv",
                    "1-10 list-Raigad.csv",
                    "1-10 list-Thane.csv",
                    "1-10 list-Yavatmal.csv",
                    "1-10 list-Aurangabad.csv",
                    "1-10 list-Chandrapur.csv",
                    "1-10 list-Jalgaon.csv",
                    "1-10 list-Gondiya.csv",
                    "1-10 list-Gadchiroli.csv",
                    "1-10 list-Osmanabad.csv",
                    "kolhapur.csv"

            };
            for (var fileName :
                    files) {
                System.out.println(fileName);
                main2(fileName);
            }

            System.out.println("document upload enabled");
        }finally {

            con.close();
        }
    }
    public static void main2( String fileName ) throws Exception {


        File f = new File(fileName);
        // always keep format of the file in csv
        Scanner sc = new Scanner(f);
        LinkedList<String> list = new LinkedList<String>();
        sc.nextLine();
        while(sc.hasNextLine()){
            String applicationNumber = sc.next().split(",")[1];
            list.add(applicationNumber);
            sc.nextLine();
        }
        System.out.println(list);

        //following line activates every application document uploading from the list variable
        list.forEach(App::setValues);


        //System.out.println(f.exists());

//        String applicationNumber =  "SM6946136547";
//        setValues(applicationNumber);





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
