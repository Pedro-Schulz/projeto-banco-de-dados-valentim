package com.app.service;

import com.app.repository.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService() {
    }

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean verificarSenha(String senhaDigatada, String senhaEsperada) {
        if (senhaDigatada == null || senhaEsperada == null) {
            return false;
        }
        return senhaDigatada.equals(senhaEsperada);
    }
}