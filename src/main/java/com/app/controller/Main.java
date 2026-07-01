package com.app.controller;

import com.app.model.*;
import com.app.repository.*;

public class Main {
    public static void main(String[] args) {

        Departamento departamento = DepartamentoRepository.buscarPorId(1L);
        System.out.println(departamento.toString());
    }
}