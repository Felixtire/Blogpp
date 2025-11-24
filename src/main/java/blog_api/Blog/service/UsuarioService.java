package blog_api.Blog.service;

import blog_api.Blog.domain.dto.entrada.DadosCadastroUsuario;
import blog_api.Blog.domain.entity.Usuario;
import blog_api.Blog.domain.repository.UserRepository;
import blog_api.Blog.service.passwordSecurity.EncoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private EncoderService encode;

    public Usuario cadastrar(DadosCadastroUsuario dadosCadastroUsuario){

        var usuarioCadastrado = new Usuario(dadosCadastroUsuario);

        usuarioCadastrado.setSenha(encode.encodePassword(usuarioCadastrado.getSenha()));

        if (!dadosCadastroUsuario.confirmarSenha().equals(usuarioCadastrado.getSenha())){
            throw new RuntimeException("As senhas precisam ser as mesmas");
        }

        return repository.save(usuarioCadastrado);


    }

    // Listar todos os usuários
    public List<Usuario> listar(){
        return repository.findAll();
    }

    // Buscar usuário por id
    public Usuario buscarPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Atualizar usuário (mesmo padrão do cadastro: valida confirmação de senha)
    public Usuario atualizar(Long id, DadosCadastroUsuario dados){
        var usuario = buscarPorId(id);

        if (!dados.confirmarSenha().equals(dados.senha())){
            throw new RuntimeException("As senhas precisam ser as mesmas");
        }

        usuario.setLogin(dados.login());

        if (dados.senha() != null){
            usuario.setSenha(encode.encodePassword(dados.senha()));
        }

        return repository.save(usuario);
    }

    // Excluir usuário
    public void excluir(Long id){
        if (!repository.existsById(id)){
            throw new RuntimeException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }

}
