package com.Euflausino.filmes.controller.user;


import com.Euflausino.filmes.dto.user.TokenResponseDTO;
import com.Euflausino.filmes.dto.user.UserCadastroDTO;
import com.Euflausino.filmes.dto.user.UserLoginDTO;
import com.Euflausino.filmes.dto.user.UserResponseDTO;
import com.Euflausino.filmes.entities.user.User;
import com.Euflausino.filmes.mapper.user.UserMapper;
import com.Euflausino.filmes.service.user.AutenticacaoService;
import com.Euflausino.filmes.service.user.TokenService;
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
    TokenService tokenService;
    public AuthController( AutenticacaoService autenticacaoService, TokenService tokenService) {
        this.autenticacaoService = autenticacaoService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody UserLoginDTO user) {
        return ResponseEntity.ok( UserMapper.mapToken( //mapeia pra un dto de resposta
                    tokenService.gerarToken( // gera o token
                            (User)autenticacaoService.autenticar(user).getPrincipal() //autentica o token gerado
                    )
                )
        );
    }

    @PostMapping("/cadastro")
    public UserResponseDTO cadastro(@Valid @RequestBody UserCadastroDTO user) {
        return autenticacaoService.cadastrarUsuario(user);
    }

}
