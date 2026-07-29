package com.app.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties");

            if (input != null) {
                properties.load(input);
                input.close();
            } else {
                File file = new File("db.properties");
                if (file.exists()) {
                    try (FileInputStream fileInput = new FileInputStream(file)) {
                        properties.load(fileInput);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar 'db.properties'");
        }
    }

    public static Connection getConnection() {
        try {
            String url = properties.getProperty("db.url");

            if (url == null || url.isBlank()) {
                throw new RuntimeException("A chave 'db.url' nao foi encontrada no db.properties!");
            }

            return DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao abrir conexao com o banco de dados Aiven!");
        }
    }
}