package blog_api.Blog.domain.dto.saida;

import blog_api.Blog.domain.dto.entrada.DadosCadastroUsuario;
import blog_api.Blog.domain.entity.Usuario;
import jakarta.validation.Valid;

import javax.print.DocFlavor;

public record DadosSaidaCadastro(String login) {

    public DadosSaidaCadastro(Usuario usuario){
        this(usuario.getLogin());
    }
}
