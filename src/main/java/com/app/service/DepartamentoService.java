package com.app.service;

import java.util.ArrayList;

import com.app.model.Candidato;
import com.app.model.Departamento;
import com.app.repository.DepartamentoRepository;

public class DepartamentoService {
    private static final DepartamentoRepository departamentoRepository = new DepartamentoRepository();

    public void salvar(Departamento departamento) {
        departamentoRepository.salvar(departamento);
    }

    public Departamento buscarPorId(Long id) {
        return departamentoRepository.buscarPorId(id);
    }

    public ArrayList<Departamento> listarTodos() {
        return departamentoRepository.listarTodos();
    }
}
