package com.app.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final Properties properties = new Properties();

    static {
        try (
                InputStream input = ConnectionFactory.class
                        .getClassLoader()
                        .getResourceAsStream("db.properties")) {

            if(input == null) {
                throw new RuntimeException("Erro ao conectar-se com 'db.properties'");
            }

            properties.load(input);

        } catch(IOException e) {
            throw new RuntimeException("Erro durante a execução de 'db.properties'", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );
        } catch(SQLException e) {
            throw new RuntimeException("Erro ao conectar-se ao banco de dados", e);
        }
    }
}
