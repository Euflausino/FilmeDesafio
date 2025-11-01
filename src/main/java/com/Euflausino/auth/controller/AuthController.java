package com.Euflausino.auth.controller;


import com.Euflausino.auth.dto.TokenResponseDTO;
import com.Euflausino.auth.dto.UserCadastroDTO;
import com.Euflausino.auth.dto.UserLoginDTO;
import com.Euflausino.auth.dto.UserResponseDTO;
import com.Euflausino.auth.entity.User;
import com.Euflausino.auth.mapper.UserMapper;
import com.Euflausino.auth.service.UserService;
import com.Euflausino.auth.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthController {

    UserService userService;
    TokenService tokenService;
    public AuthController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody UserLoginDTO user) {
        return ResponseEntity.ok( UserMapper.mapToken( //mapeia pra un dto de resposta
                    tokenService.gerarToken( // gera o token
                            (User) userService.autenticar(user).getPrincipal() //autentica o token gerado
                    )
                )
        );
    }

    @PostMapping("/cadastro")
    public UserResponseDTO cadastro(@Valid @RequestBody UserCadastroDTO user) {
        return userService.cadastrarUsuario(user);
    }

}
