package de.hda.plusrep.modules;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    Connection con;
    private static String ip = "postgres.fbi.h-da.de";// this is the host ip that your data base exists on you can use 10.0.2.2 for local host                                                    found on your pc. use if config for windows to find the ip if the database exists on                                                    your pc
    private static String port = "5432";// the port sql server runs on
    private static String username = "plusrep";// the user name
    private static String password = "plusR3p";// the password
    private static String database = "stomarfaa";// the data base name

    private static String Classes = "org.postgresql.Driver";

    private static String url = "jdbc:postgresql://"+ip+":"+port+"/"+database;


    public Connection getCon() {
        return connection;
    }

    private Connection connection = null;



        public void start() {


            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                Class.forName(Classes);
                connection = DriverManager.getConnection(url, username,password);
                Log.i("Test","Connected");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Log.e("Test", "Class fail");
            } catch (SQLException e) {
                e.printStackTrace();
                Log.e("Test","Connected no");
            }
        }
    }