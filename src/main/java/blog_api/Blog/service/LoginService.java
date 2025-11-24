package blog_api.Blog.service;

import blog_api.Blog.domain.dto.entrada.DadosLoginEntrada;
import blog_api.Blog.domain.dto.saida.TokenJwt;
import blog_api.Blog.domain.entity.Usuario;
import blog_api.Blog.domain.repository.UserRepository;
import blog_api.Blog.infra.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    public TokenJwt logar(DadosLoginEntrada dados){

        var user = userRepository.findByLogin(dados.login()).orElseThrow(()->new RuntimeException("Usuário não encontrado."));

        var token = tokenService.gerarToken(user);

        return new TokenJwt(token);

    }


}
