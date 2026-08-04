package com.app.service;

import com.app.enums.Perfis;
import com.app.model.Funcionario;
import com.app.model.Vaga;
import com.app.repository.UsuarioRepository;
import com.app.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioService {
    private final UsuarioRepository usuarioRepository = new UsuarioRepository();
    private final FuncionarioService funcionarioService = new FuncionarioService();

    public void criarUsuario(String cpf, String senha) {
        try {
            validarSenha(senha);
            String senhaHash = encriptar(senha);

            Funcionario funcionario = funcionarioService.buscarPorCpf(cpf);
            Vaga vaga = funcionario.getVaga();

            Perfis perfil = Perfis.VIEWER;
            if (vaga.getCargo().equalsIgnoreCase("presidente")
                    || vaga.getCargo().equalsIgnoreCase("diretor")
                    || vaga.getCargo().equalsIgnoreCase("gerente")) {

                perfil = Perfis.ADMIN;
            } else if (vaga.getCargo().equalsIgnoreCase("coordenador")
                    || vaga.getCargo().equalsIgnoreCase("supervisor")
                    || vaga.getCargo().equalsIgnoreCase("especialista")) {

                perfil = Perfis.USER;
            }

            Usuario usuario = new Usuario(cpf, senhaHash, perfil, funcionario.getAtivo(), funcionario.getIdFuncionario());
            usuarioRepository.salvar(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao criar usuário!");
        }
    }

    public Usuario buscarPorCpf(String cpf) {
        return usuarioRepository.buscarPorCpf(cpf);
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
