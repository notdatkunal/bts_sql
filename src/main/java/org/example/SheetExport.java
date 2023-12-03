package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class SheetExport {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");


           Connection con = DriverManager.getConnection("jdbc:mysql://103.39.132.102:3306/ah_mahabms", "ah_mahabms", "Atngtuta9m#o7jLo");
           try{
                getDefendants(con,"ah_mahabms");}finally {

               con.close();
           }
            System.out.println("hi");
    }

    public static void getDefendants(Connection con , String db) throws Exception  {



        @SuppressWarnings("unused")
        Workbook readWorkbook = WorkbookFactory.create(new FileInputStream("test.xlsx") );
        @SuppressWarnings("resource")
        Workbook writeWorkbook = new HSSFWorkbook();
        Sheet desSheet = writeWorkbook.createSheet("new sheet");

        Statement stmt = null;
        ResultSet rs = null;
        try{
            String query =
            """
                            SELECT DISTINCT f.farmer_number
                            , fa.application_number
                            , f.first_name
                            , f.middle_name
                            , f.last_name
                            , f.aadhar_number
                            , f.phone_number
                            ,  v.village_name
                            , t.taluka_name
                            , ai.final_status
                            , ai.application_item_id
                            , ai.view_sequence
                            , ai.tsp_sequence
                            , ai.tsp_sequence_no
                            , f.gender
                            , ai.selection_reason
                            , c.caste
                            , f.caste_id
                            ,fa.financial_year
                            FROM farmer f, farmer_application fa, village v, taluka t, application_items ai, caste c \s
                                        
                            WHERE f.farmer_id = fa.farmer_id
                            AND fa.taluka_id = t.taluka_id
                            AND c.caste_id = f.caste_id 
                            AND fa.village_id = v.village_id 
                             AND
                             (
                              ai.final_status = 'Taluka Patra'
                              OR
                             ai.final_status = 'Taluka Waiting'
                            ) 
                             
                            AND fa.application_id = ai.application_id
                             AND ai.doc_upload_permission !=1
                            ORDER BY LENGTH(ai.view_sequence), ai.view_sequence ASC
                          
                            ;
                            
                    """;

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            Row desRow1 = desSheet.createRow(0);
            for(int col=0 ;col < columnsNumber;col++) {
                Cell newpath = desRow1.createCell(col);
                newpath.setCellValue(rsmd.getColumnLabel(col+1));
            }
            while(rs.next()) {
                System.out.println("Row number" + rs.getRow() );
//                if(rs.getRow()>1000)
//                    break;
                Row desRow = desSheet.createRow(rs.getRow());
                for(int col=0 ;col < columnsNumber;col++) {
                    Cell newpath = desRow.createCell(col);
                    newpath.setCellValue(rs.getString(col+1));
                }
                FileOutputStream fileOut = new FileOutputStream("test.xlsx");
                writeWorkbook.write(fileOut);
                fileOut.close();
            }
        }
        catch (SQLException e) {
            System.out.println("Failed to get data from database");
        }
    }


}
