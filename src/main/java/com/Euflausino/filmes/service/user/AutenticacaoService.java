package com.Euflausino.filmes.service.user;

import com.Euflausino.filmes.dto.user.UserCadastroDTO;
import com.Euflausino.filmes.dto.user.UserLoginDTO;
import com.Euflausino.filmes.dto.user.UserResponseDTO;
import com.Euflausino.filmes.entities.user.User;
import com.Euflausino.filmes.mapper.user.UserMapper;
import com.Euflausino.filmes.repository.user.UsuarioRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    TokenService tokenService;

    public AutenticacaoService(UsuarioRepository repository, @Lazy AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override //spring chama esse método automaticamente durante o login
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario inválido ou não encontrado."));
    }

    public UserResponseDTO cadastrarUsuario(UserCadastroDTO user) {
        User userNovo = UserMapper.mapToEntity(user);
        String rawPassword = user.senha();
        String encoded = passwordEncoder.encode(rawPassword);
        userNovo.setPassword(encoded);
        repository.save(userNovo);
        return UserMapper.mapToDTO(userNovo);
    }

    public Authentication autenticar(UserLoginDTO user) {
        var token = new UsernamePasswordAuthenticationToken(user.username(), user.senha());  //gera um token de autenticacao
        return authenticationManager.authenticate(token); //autentica o token, chamando a classe loadByUsername
        //Compara a senha informada com a senha codificada no banco.
        //Se tudo estiver certo, retorna um objeto Authentication autenticado (com o usuário dentro dele).
    }
}
