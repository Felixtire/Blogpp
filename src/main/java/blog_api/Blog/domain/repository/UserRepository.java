package blog_api.Blog.domain.repository;

import blog_api.Blog.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Usuario, Long> {
}
