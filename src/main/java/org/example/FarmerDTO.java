package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FarmerDTO {
    private String farmerId;
    private Long aadhar;
    private Long age ;
    private String firstName ;
    private String middleName ;
    private String lastName ;
    private String gender ;
    private Long contact;
    private String email ;
    private String district ;
    private String taluka ;
    private String village ;
    private String caste ;
    private String casteCertificate ;
    private String disability ;
    private String belowPovertyLine ;
    private String education ;
    private String rationCard ;
    private String bankName ;
    private Long accNumber ;
    private String ifsc ;
    private String branch ;

    public String getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }

    public Long getAadhar() {
        return aadhar;
    }

    public void setAadhar(Long aadhar) {
        this.aadhar = aadhar;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getCaste() {
        return caste;
    }

    public void setCaste(String caste) {
        this.caste = caste;
    }

    public String getCasteCertificate() {
        return casteCertificate;
    }

    public void setCasteCertificate(String casteCertificate) {
        this.casteCertificate = casteCertificate;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getBelowPovertyLine() {
        return belowPovertyLine;
    }

    public void setBelowPovertyLine(String belowPovertyLine) {
        this.belowPovertyLine = belowPovertyLine;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getRationCard() {
        return rationCard;
    }

    public void setRationCard(String rationCard) {
        this.rationCard = rationCard;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Long getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(Long accNumber) {
        this.accNumber = accNumber;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public FarmerDTO() {
    }

    public FarmerDTO( Long aadhar, Long age, String firstName, String middleName, String lastName, String gender, Long contact, String email, String district, String taluka, String village, String caste, String casteCertificate, String disability, String belowPovertyLine, String education, String rationCard, String bankName, Long accNumber, String ifsc, String branch) {

        this.aadhar = aadhar;
        this.age = age;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.contact = contact;
        this.email = email;
        this.district = district;
        this.taluka = taluka;
        this.village = village;
        this.caste = caste;
        this.casteCertificate = casteCertificate;
        this.disability = disability;
        this.belowPovertyLine = belowPovertyLine;
        this.education = education;
        this.rationCard = rationCard;
        this.bankName = bankName;
        this.accNumber = accNumber;
        this.ifsc = ifsc;
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "FarmerDTO{" +
                "date='" + farmerId + '\'' +
                ", aadhar=" + aadhar +
                ", age=" + age +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", contact=" + contact +
                ", email='" + email + '\'' +
                ", district='" + district + '\'' +
                ", taluka='" + taluka + '\'' +
                ", village='" + village + '\'' +
                ", caste='" + caste + '\'' +
                ", casteCertificate='" + casteCertificate + '\'' +
                ", disability='" + disability + '\'' +
                ", belowPovertyLine='" + belowPovertyLine + '\'' +
                ", education='" + education + '\'' +
                ", rationCard=" + rationCard +
                ", bankName='" + bankName + '\'' +
                ", accNumber=" + accNumber +
                ", ifsc='" + ifsc + '\'' +
                ", branch='" + branch + '\'' +
                '}';
    }

    public void update(Connection con) {

        try{
            findFarmer(con);
            System.out.println("welcome farmer");
            System.out.println(this.getBelowPovertyLine());
            StringBuffer sql = new StringBuffer();
//            sql.append( "UPDATE farmer"
//                        +"SET "
//
//                        +"WHERE farmer_id='"+this.farmerId+"'");





        }
        catch (SQLException e){
        return;
        }




    }

    private void findFarmer(Connection con) throws SQLException {


        String sql = "SELECT  farmer_id FROM farmer WHERE  aadhar_number = '"+this.aadhar+"' or phone_number='"+this.contact+"'; ";
        PreparedStatement ps = con.prepareStatement(sql);
        if(!ps.execute()) {
            throw new SQLException("failed query");
        }
        System.out.println(sql);
        var result = ps.getResultSet();
        while(result.next()){
            var string = result.getString("farmer_id");
            if(string==null)
                throw new SQLException("no farmer found");
            this.setFarmerId(string);
            System.out.println(string+" this is id");
        }
        
        ps.close();
    }
}
