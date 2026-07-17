package com.app.service;

import com.app.repository.UsuarioRepository;
import com.app.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void criarUsuario(String cpf, String senha) {
        validarSenha(senha);
        String senhaHash = encriptar(senha);
        Usuario usuario = new Usuario(cpf, senhaHash);
        usuarioRepository.salvar(usuario);
    }

    public String encriptar(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt(12));
    }

    public boolean validarSenha(String senha) {
        return true;
    }

    public boolean verificarSenha(String senha, String cpf) {
        Usuario usuario = usuarioRepository.buscarPorCpf(cpf);
        return BCrypt.checkpw(senha, usuario.getSenhaHash());
    }
}
