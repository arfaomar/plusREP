package de.hda.plusrep.modules;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Postgre {
    private static final String ip = "postgres.fbi.h-da.de";// this is the host ip that your data base exists on you can use 10.0.2.2 for local host                                                    found on your pc. use if config for windows to find the ip if the database exists on                                                    your pc
    private static final String port = "5432";// the port sql server runs on
    private static final String username = "plusrep";// the user name
    private static final String password = "plusR3p";// the password
    private static final String database = "stomarfaa";// the data base name
    private static final String Classes = "org.postgresql.Driver"; // driver for PostgreSQL
    private static final String url = "jdbc:postgresql://"+ip+":"+port+"/"+database;
    private Connection connection = null;

    public Postgre() {
        this.start();
    }


    public Connection getCon() {
        return connection;
    }

    public void start() {
        String logTag = "PostgreDB: ";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username,password);
            Log.i(logTag,"Connected");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.e(logTag, "Class fail");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(logTag,"Failed to Connect");
        }
    }

    @Override
    protected void finalize() {
        try {
            connection.close();
        } catch (Exception e) {
            Log.e("PostgreDB:(finalize)", e.getMessage());
        }
    }

}
