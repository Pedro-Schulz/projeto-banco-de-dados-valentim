package com.app.service;

import java.util.ArrayList;

import com.app.enums.StatusVinculos;
import com.app.model.Candidato;
import com.app.model.Departamento;
import com.app.repository.DepartamentoRepository;
import com.app.repository.VagaRepository;
import com.app.util.Validar;

public class DepartamentoService {
    private final DepartamentoRepository departamentoRepository = new DepartamentoRepository();
    private final VagaService vagaService = new VagaService();

    public void salvar(Departamento departamento) {
        Validar.validar(departamento);
        departamentoRepository.salvar(departamento);
    }

    public Departamento buscarPorId(Long id) {
        return departamentoRepository.buscarPorId(id);
    }

    public ArrayList<Departamento> listarTodos() {
        return departamentoRepository.listarTodos();
    }

    public StatusVinculos desativar(Long id) {
        if(vagaService.vinculoDepartamento(id)) {
            return StatusVinculos.POSSUI_VINCULOS;
        }
        departamentoRepository.desativar(id);
        return StatusVinculos.SUCESSO;
    }
}
