package blog_api.Blog.controller;

import blog_api.Blog.domain.dto.entrada.DadosLoginEntrada;
import blog_api.Blog.service.LoginService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthUser {

    @Autowired
    private LoginService service;

    @PostMapping
    @Transactional
    public ResponseEntity login(@RequestBody @Valid DadosLoginEntrada dados){

        var userLogin = service.logar(dados);

        return ResponseEntity.ok(userLogin);

    }





}
