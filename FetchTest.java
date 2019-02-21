package com.turkekspres.harcamayonetimi;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FetchTest {

    public final static String DB_URL = "jdbc:jtds:sqlserver://127.0.0.1:1433;database=u7899378_TestDB;";
    public final static String DB_DRIVER_JTDS = "net.sourceforge.jtds.jdbc.Driver";
    public final static String DB_USERNAME = "sa";
    public final static String DB_PASSWORD = "Test123456";

    private Connection connection = null;
    private PreparedStatement preStatement = null;
    private ResultSet result;

    @Test
    public void fetchTest(){
        try{
            Class.forName(DB_DRIVER_JTDS);
            connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);

            //Data convert Binary to Image and Download pc
            File file=new File("C:\\Users/koray.saldere/Desktop/testImg.jpg");
            FileOutputStream fos = new FileOutputStream(file);
            byte b[];
            Blob blob;

            preStatement = connection.prepareStatement("Select * From APP_image_table");
            result = preStatement.executeQuery();

            while(result.next()){
                blob=result.getBlob("image");
                b=blob.getBytes(1,(int)blob.length());
                fos.write(b);
            }

            preStatement.close();
            fos.close();
            connection.close();
            }catch(Exception e){
            e.printStackTrace();
        }
    }

}
