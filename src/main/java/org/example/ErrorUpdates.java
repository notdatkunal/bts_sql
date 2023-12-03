package org.example;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ErrorUpdates {
    public static String getString(Cell cell) {
        if (cell == null) {
            System.out.println("\n No value for the cell \n ");
            return null;
        }
        return cell.toString().trim();
    }

    public static Long getLong(Cell cell){
        Double number;
        try {
            number = Double.parseDouble(cell.toString().trim());
        }catch (NullPointerException | NumberFormatException e){
            if(null!=cell)
            System.out.println("\n No value for "+cell.getRowIndex()+"th entry and "+cell.getColumnIndex()+" column \n ");
            return  null;
        }
        return number.longValue();
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, InvalidFormatException {

        File file = new File("MAHABMS - Error Correction (Responses).xlsx");
        FileInputStream in = new FileInputStream(file);
        Workbook workbook = WorkbookFactory.create(in);
        Sheet sheet = workbook.getSheetAt(0);

        int rowNumber = sheet.getLastRowNum() + 1;
        List<FarmerDTO> farmers = new LinkedList<FarmerDTO>();
        for (int j = 1; j < rowNumber; j++) {
            Row row = sheet.getRow(j);
            Long aadhar = getLong(row.getCell(1));
            Long age = getLong(row.getCell(2));
            String firstName = getString(row.getCell(3));
            String middleName = getString(row.getCell(4));
            String lastName = getString(row.getCell(5));
            String gender = getString(row.getCell(6));
            Long contact = getLong(row.getCell(7));
            String email = getString(row.getCell(8));
            String district = getString(row.getCell(9));
            String taluka = getString(row.getCell(10));
            String village = getString(row.getCell(11));
            String caste = getString(row.getCell(12));
            String casteCertificate = getString(row.getCell(13));
            String disability = getString(row.getCell(14));
            String belowPovertyLine = getString(row.getCell(15));
            String education = getString(row.getCell(16));
            String rationCard = getString(row.getCell(17));
            String bankName = getString(row.getCell(18));
            Long accNumber = getLong(row.getCell(19));
            String ifsc = getString(row.getCell(20));
            String branch = getString(row.getCell(21));
            FarmerDTO farmer = new FarmerDTO(aadhar,age,firstName,middleName,lastName,gender,contact,email,district,taluka,village,caste,casteCertificate,disability,belowPovertyLine,education,rationCard,bankName,accNumber,ifsc,branch);

            if(farmer.getAadhar()==null&&farmer.getContact()==null)
            {
                System.out.println("farmer that cannot be added "+farmer+" from index "+j);
                continue;
            }


            farmers.add(farmer);
           // System.out.println(j+"    "+farmer);

        }
       farmers = new ArrayList<>(farmers);
        updateFarmer(farmers);
       // farmers.forEach(farmer-> System.out.println(farmer.getAadhar()));






    }

    public static void updateFarmer(List<FarmerDTO> farmers) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        try{
            con = DriverManager.getConnection("jdbc:mysql://103.39.132.102:3306/ah_mahabms", "ah_mahabms", "Atngtuta9m#o7jLo");
            con.setAutoCommit(false);


            farmers.get(0).update(con);
           // farmers.forEach(farmer->farmer.update(con));







        ResultSet result = con.createStatement().executeQuery("select * from farmer LIMIT 10;");
        con.commit();
        con.setAutoCommit(true);
        }
        finally{

            con.close();
        }
    }
    private static   Connection con;

}