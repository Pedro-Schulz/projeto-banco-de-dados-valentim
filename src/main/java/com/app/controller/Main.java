package com.app.controller;

import com.app.model.*;
import com.app.repository.*;

public class Main {
    public static void main(String[] args) {

        DadosBancarios d = DadosBancariosRepository.buscarPorId(1L);
        System.out.println(d.toString());
    }
}