package blog_api.Blog.controller;

import blog_api.Blog.domain.dto.entrada.DadosCadastroUsuario;
import blog_api.Blog.domain.dto.saida.DadosSaidaCadastro;
import blog_api.Blog.domain.entity.Usuario;
import blog_api.Blog.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cadastrar")
public class CadastrarUsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    @Transactional
    @Operation(summary = "Cadastrar usuários")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriComponentsBuilder){

        var usuarioCadastro = service.cadastrar(dados);

        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuarioCadastro.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosSaidaCadastro(usuarioCadastro));

    }

    @GetMapping
    @Operation(summary = "Listar usuários")
    public ResponseEntity listar(){

        List<Usuario> usuarios = service.listar();

        var lista = usuarios.stream().map(DadosSaidaCadastro::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por id")

    public ResponseEntity buscarPorId(@PathVariable Long id){
        var usuario = service.buscarPorId(id);
        return ResponseEntity.ok().body(new DadosSaidaCadastro(usuario));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualizar usuário")

    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosCadastroUsuario dados){

        var usuarioAtualizado = service.atualizar(id, dados);

        return ResponseEntity.ok().body(new DadosSaidaCadastro(usuarioAtualizado));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Excluir usuário")

    public ResponseEntity excluir(@PathVariable Long id){

        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

}
