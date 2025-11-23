package blog_api.Blog.domain.repository;

import blog_api.Blog.domain.entity.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem,Long> {
}
