package com.solvd.connection;

public class Connection {

    private String url;
    private String username;
    private String password;

    public Connection(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void connect() {
        //Connect to the database using the url, username, and password
    }

    public void close() {
        //Close the connection
    }
}