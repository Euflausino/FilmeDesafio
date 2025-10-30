package com.Euflausino.filmes.controller;


import com.Euflausino.filmes.dto.UserCadastroDTO;
import com.Euflausino.filmes.dto.UserLoginDTO;
import com.Euflausino.filmes.dto.UserResponseDTO;
import com.Euflausino.filmes.service.AutenticacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthController {

    AutenticacaoService autenticacaoService;
    public AuthController( AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody UserLoginDTO user) {
        autenticacaoService.autenticar(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/cadastro")
    public UserResponseDTO cadastro(@Valid @RequestBody UserCadastroDTO user) {
        return autenticacaoService.cadastrarUsuario(user);
    }

}
