package com.academy.four.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * U momentu kada se instancira(poziva se konstruktor) connectionPool INSTANCE objekat
 * treba da kreira 10/20(maxConnections) Connection objekata i da čuva otvorenih 10 konekcija.
 */
public class ConnectionPool {
    /******SINGLETON*******/
    private static ConnectionPool INSTANCE;
    private static final int maxConnections = 10;

    private final List<Connection> availableConnections = new ArrayList<>();
    private final List<Connection> inUseConnections = new ArrayList<>();


    private ConnectionPool() {
        for(int i = 0; i < maxConnections; i++) {
            Connection connection = createConnection();
            availableConnections.add(connection);
        }
    }


    private Connection createConnection() {
        try {
            String url = ConnectionProperty.URL.get();
            String username = ConnectionProperty.USERNAME.get();
            String password = ConnectionProperty.PASSWORD.get();
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        if(availableConnections.isEmpty()) {
            throw new RuntimeException("No connections available");
        }
        Connection connection = availableConnections.getLast();
        availableConnections.remove(connection);
        inUseConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        if(connection == null) {
            return false;
        }
        availableConnections.add(connection);
        inUseConnections.remove(connection);
        return true;
    }

    public static ConnectionPool instance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPool();
        }
        return INSTANCE;
    }
}
