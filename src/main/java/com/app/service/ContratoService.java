package com.app.service;

import com.app.repository.ContratoRepository;

public class ContratoService {
    private final ContratoRepository contratoRepository = new ContratoRepository();

    public void desativar(Long id) {
        contratoRepository.desativar(id);
    }
}
