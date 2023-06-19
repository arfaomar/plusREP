package de.hda.plusrep.modules;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class Postgre {
    private Connection connection;

    private final String host = "postgres.fbi.h-da.de";

    private final String database = "stomarfaa";
    private final int port = 5432;
    private final String user = "plusrep";
    private final String pass = "plusR3p";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;

    public Postgre() {
        this.url = String.format(this.url, this.host, this.port, this.database);
        connect();
        //this.disconnect();
        Log.i("SQL_DB","connection status: " + String.valueOf(status));
    }

    private void connect()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Class.forName("org.postgresql.Driver");
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;
                    Log.i("SQL_DB","connected: " + String.valueOf(status));
                }
                catch (Exception e)
                {
                    status = false;
                    Log.e("SQL_DB", e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.status = false;
        }
    }

    public Connection getExtraConnection()
    {
        Connection c = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, pass);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }
}
