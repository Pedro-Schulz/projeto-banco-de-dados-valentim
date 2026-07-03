package com.app.service;

import com.app.repository.DadosBancariosRepository;

public class DadosBancariosService {
    private final DadosBancariosRepository dadosBancariosRepository = new DadosBancariosRepository();

    public void desativar(Long id) {
        dadosBancariosRepository.desativar(id);
    }
}
