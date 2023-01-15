package com.solvd.connection;

public class ConnectionWorker implements Runnable {

    private CustomConnectionPool pool;

    public ConnectionWorker(CustomConnectionPool pool) {
        this.pool = pool;
    }

    public void run() {
        try {
            Connection connection = pool.getConnection();
            System.out.println("connection is created by thread " + Thread.currentThread().getName().substring(Thread.currentThread().getName().lastIndexOf("-") + 1));
            Thread.sleep(1000); // simulate connection usage
            pool.releaseConnection(connection);
            System.out.println("connection is released by thread " + Thread.currentThread().getName().substring(Thread.currentThread().getName().lastIndexOf("-") + 1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
