package com.app.controller;

import com.app.model.*;
import com.app.repository.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        FuncionarioRepository fr = new FuncionarioRepository();
        VagaRepository vr = new VagaRepository();
        DepartamentoRepository dr = new DepartamentoRepository();

        fr.deletar(50L);

    }
}