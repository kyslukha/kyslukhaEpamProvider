package ua.epam.provider.connection;


import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;

import static ua.epam.provider.connection.ConnectionConstants.*;

public class DBManager {

    private static final Logger log = Logger.getLogger("DBManager.class");

    private static DBManager instance;

    public DBManager() {

    }


    public static synchronized DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            PoolProperties p = new PoolProperties();
            p.setUrl(URL);
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUsername(USER);
            p.setPassword(PASSWORD);
            p.setMaxActive(30);
            p.setDefaultAutoCommit(false);
            p.setMaxWait(5000);
            p.setMaxIdle(5);
            p.setMinIdle(2);
            DataSource datasource = new DataSource();
            datasource.setPoolProperties(p);
            connection = datasource.getConnection();
        } catch (SQLException e) {
            log.error("Cannot obtain a connection from the pool", e);
        }
        return connection;
    }


    public void commitAndClose(Connection connection) {
        try {
            connection.commit();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void rollbackAndClose(Connection connection) {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
