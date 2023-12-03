package org.example;



import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class FarmerUpdates {
    private static String URL = "https://script.googleusercontent.com/macros/echo?user_content_key=tTb3pmygxbjTuMcif95BtkHziry2HYXV8aCFCuMromwt1m_sGknWmG8Dh_agq4SaFEeu63dUsZQWp2tDFijOGw6cfDoBwWv7m5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnCc8DTG0nuIDH4uAxBVUSKXL9ewYFmxxQqPwrTIlZKdEqtNK8Y9NwOVgDkagEhAiN8lO3Xfhp1QYckqwJjjYfoBlBLShXJG4bw&lib=MQ5d4zcgxuBebenW2gykdhFhwL89NOhPq";

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

       // URL url = new URL(URI);


       
      var request = HttpRequest.newBuilder().GET().uri(URI.create(URL)).build();
      var client = HttpClient.newBuilder().followRedirects(HttpClient.Redirect.ALWAYS).build();
      var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        HttpHeaders headers = response.headers();
        headers.map().forEach((k, v) -> System.out.println(k + ":" + v));
        //System.out.println(response.body());
        Gson gson = new Gson();
//        JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);
//        System.out.println(jsonResponse);
//        System.out.println(jsonResponse.isJsonObject());

        // System.out.println(response.body());

    }

    public static void main2(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://103.39.132.102:3306/ah_mahabms","ah_mahabms","Atngtuta9m#o7jLo");
        ResultSet result = con.createStatement().executeQuery("select * from farmer LIMIT 10;");

        for (int i = 1;i<=result.getMetaData().getColumnCount();i++)
            System.out.println(result.getMetaData().getColumnName(i));
//        while (result.next()){
//            //System.out.println(result.getString(2));
//            for (int i = 1;i<11;i++){
//                System.out.println(result.getString(i));
//            }
//            System.out.println("");
//        }
//        System.out.println("hello");
        con.close();
    }
}
