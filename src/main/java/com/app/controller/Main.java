package com.app.controller;

import com.app.*;
import com.app.config.ConnectionFactory;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection c = ConnectionFactory.getConnection();
        System.out.println("Conectado!");
    }
}