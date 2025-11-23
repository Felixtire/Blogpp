package blog_api.Blog.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "postagem")
@Table(name = "postagens")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String texto;
    private String autor;
}
