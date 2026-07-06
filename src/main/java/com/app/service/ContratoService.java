package com.app.service;

import com.app.repository.ContratoRepository;

public class ContratoService {
    private final ContratoRepository contratoRepository = new ContratoRepository();

    public String desativar(Long id_contrato) {
        contratoRepository.desativar(id_contrato);
        return "Contrato desativado com sucesso!";
    }
}