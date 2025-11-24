package blog_api.Blog.infra;

import blog_api.Blog.domain.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {

    @Value("${app.jwt.secret}")
    String secretKey;



    public String gerarToken(Usuario usuario){


        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.create()
                    .withIssuer("Blog_API")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataLimite())
                    .withClaim("id", usuario.getId())
                    .sign(algorithm);
        }catch (Exception exception){
            throw new RuntimeException("Erro ao gerar o token");
        }
    }

    private Instant dataLimite() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));

    }

    public String getSubject(String tokenJwt){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("Blog_API")
                    .build()
                    .verify(tokenJwt)
                    .getSubject();
        }catch (Exception exception){
            throw new RuntimeException("Token inválido ou expirado");
        }
    }


}
