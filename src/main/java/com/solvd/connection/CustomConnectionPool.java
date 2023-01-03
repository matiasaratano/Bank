package com.solvd.connection;

import java.util.concurrent.ConcurrentLinkedQueue;

public class CustomConnectionPool {
    private ConcurrentLinkedQueue<Connection> pool;

    public CustomConnectionPool(int size) {
        pool = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < size; i++) {
            pool.add(createConnection());
        }
    }

    public Connection getConnection() {
        while (pool.isEmpty()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                // Do nothing
            }
        }
        return pool.poll();
    }

    public void releaseConnection(Connection connection) {
        pool.add(connection);
    }

    private Connection createConnection() {
        // Create a new connection and return it
        return null;
    }

}
