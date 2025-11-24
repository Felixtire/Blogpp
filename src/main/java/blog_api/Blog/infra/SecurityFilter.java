package blog_api.Blog.infra;


import blog_api.Blog.domain.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;


    @Autowired
    private UserRepository repository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var path = request.getRequestURI();
        var method = request.getMethod();

        if (
                (path.equals("/login") || path.equals("/cadastrar")) ||
                (path.equals("/login/redifinir-senha") || path.equals("/login/redifinir-senha/")) &&
                        (method.equals("POST") || method.equals("OPTIONS"))
        ){
            filterChain.doFilter(request,response);
            return;
        }
        var tokenJwt = recuperarToken(request);

        if (tokenJwt != null){
            var subject = tokenService.getSubject(tokenJwt);
            var usuario = repository.findByLogin(subject)
                    .orElseThrow(()-> new RuntimeException("Usuário não encontrado"));

            var authentication = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
            org.springframework.security.core.context.SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);


    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null){
            return authorizationHeader.replace("Bearer ","").trim();
        }
        return null;
    }
}
