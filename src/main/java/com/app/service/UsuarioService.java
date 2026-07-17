package com.app.service;

import com.app.repository.UsuarioRepository;
import com.app.model.Usuario;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void salvar(Usuario usuario) {
        validarSenha(usuario.getSenha());
        usuarioRepository.salvar(usuario);
    }

    public void encriptar() {};

    public boolean validarSenha(String senha) {
        return true;
    }
}
