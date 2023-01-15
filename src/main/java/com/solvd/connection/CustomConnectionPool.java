package com.solvd.connection;

import java.util.concurrent.LinkedBlockingQueue;


public class CustomConnectionPool {

    public static final int MAX_CONNECTIONS = 5;
    private LinkedBlockingQueue<Connection> connections;
    private static CustomConnectionPool instance;

    public CustomConnectionPool() {
        connections = new LinkedBlockingQueue<>(MAX_CONNECTIONS);
        //Initialize pool with 5 connections
        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            Connection connection = new Connection("a", "a", "a");
            this.connections.add(connection);
        }
    }

    public static CustomConnectionPool getInstance() {
        if (instance == null) {
            instance = new CustomConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        synchronized (this) {
            while (connections.size() == 0) {
                try {
                    System.out.println("no available connections for thread " + Thread.currentThread().getName().substring(Thread.currentThread().getName().lastIndexOf("-") + 1) + " will wait for next free one");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return connections.poll();
        }
    }

    public void releaseConnection(Connection connection) {
        synchronized (this) {
            connections.add(connection);
            notifyAll();
        }
    }
}