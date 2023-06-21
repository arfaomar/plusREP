package de.hda.plusrep.modules;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;

public class Postgre {
    private static String ip = "postgres.fbi.h-da.de";// this is the host ip that your data base exists on you can use 10.0.2.2 for local host                                                    found on your pc. use if config for windows to find the ip if the database exists on                                                    your pc
    private static String port = "5432";// the port sql server runs on
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";// the driver that is required for this connection use                                                                           "org.postgresql.Driver" for connecting to postgresql
    private static String database = "stomarfaa";// the data base name
    private static String username = "plusrep";// the user name
    private static String password = "plusR3p";// the password
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database; // the connection url string



    private Connection connection = null;




    public  Postgre() {


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username,password);
            Log.i("test", "conx");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public Connection getConnection() {
        return connection;
    }

}
