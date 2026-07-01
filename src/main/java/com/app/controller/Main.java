package com.app.controller;

import com.app.model.*;
import com.app.repository.*;

public class Main {
    public static void main(String[] args) {

        Vaga v = VagaRepository.buscarPorId(1L);
        System.out.println(v.toString());
    }
}