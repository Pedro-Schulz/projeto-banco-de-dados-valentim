package com.app.service;

import java.util.ArrayList;

import com.app.enums.StatusVinculos;
import com.app.model.Candidato;
import com.app.model.Departamento;
import com.app.repository.DepartamentoRepository;
import com.app.repository.VagaRepository;

public class DepartamentoService {
    private final DepartamentoRepository departamentoRepository;
    private final VagaRepository vagaRepository;

    public DepartamentoService(DepartamentoRepository departamentoRepository, VagaRepository vagaRepository) {
        this.departamentoRepository = departamentoRepository;
        this.vagaRepository = vagaRepository;
    }

    public void salvar(Departamento departamento) {
        departamentoRepository.salvar(departamento);
    }

    public Departamento buscarPorId(Long id) {
        return departamentoRepository.buscarPorId(id);
    }

    public ArrayList<Departamento> listarTodos() {
        return departamentoRepository.listarTodos();
    }

    public StatusVinculos desativar(Long id) {
        if(vagaRepository.vinculoDepartamento(id)) {
            return StatusVinculos.POSSUI_VINCULOS;
        } else {
            return StatusVinculos.SUCESSO;
        }
    }
}
