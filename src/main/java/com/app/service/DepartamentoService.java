package com.app.service;

import java.util.ArrayList;
import com.app.model.Departamento;
import com.app.repository.DepartamentoRepository;

public class DepartamentoService {
    private static final DepartamentoRepository departamentoRepository = new DepartamentoRepository();

    public ArrayList<Departamento> listarTodos() {
        return departamentoRepository.listarTodos();
    }
}
