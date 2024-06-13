package com.crime.repository;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/* Обеспечение централизованного доступа к соединению с БД */

public class ConnectionFactory {

    public static Connection getConnection() {
        InitialContext context = null;
        try {
            context = new InitialContext();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        DataSource dataSource = null;
        try {
            dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/postgres");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        if (dataSource == null) {
            throw new RuntimeException("Data source not found!");
        }
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
