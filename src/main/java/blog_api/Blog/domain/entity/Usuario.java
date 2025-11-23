package blog_api.Blog.domain.entity;

import blog_api.Blog.domain.dto.entrada.DadosCadastroUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "usuario")
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String senha;

    public Usuario(DadosCadastroUsuario dadosCadastroUsuario) {

        this.login= dadosCadastroUsuario.login();
        this.senha= dadosCadastroUsuario.senha();


    }
}
