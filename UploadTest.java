package com.turkekspres.harcamayonetimi;

import android.os.Environment;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class UploadTest {

    public final static String DB_URL = "jdbc:jtds:sqlserver://127.0.0.1:1433;database=u7899378_TestDB;";
    public final static String DB_DRIVER_JTDS = "net.sourceforge.jtds.jdbc.Driver";
    public final static String DB_USERNAME = "sa";
    public final static String DB_PASSWORD = "Test123456";

    private Connection connection = null;
    private PreparedStatement preStatement = null;

    @Test
        public void uploadImageTest(){
            try{
                // Getting image Android DCIM folder and convert Image to Binary. Upload APP_image_table DB
                Class.forName(DB_DRIVER_JTDS);
                connection = DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASSWORD);
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
                // This line test with computer path case ==> File file=new File("C:\\testImg.jpg");
                File file = new File(path,"/testImg.jpg");
                FileInputStream fis = new FileInputStream(file);
                preStatement = connection.prepareStatement("insert into APP_image_table (name,image) values(?,?)");
                preStatement.setString(1,"image 1");
                preStatement.setBinaryStream(2,fis,(int)file.length());
                preStatement.executeUpdate();
                preStatement.close();
                fis.close();
                connection.close();
                }catch(Exception e){
                e.printStackTrace();
            }
        }
        // Test Success check DB table ==> APP_image_table
    }

