package com.Euflausino.auth.config;

import com.Euflausino.auth.repository.UsuarioRepository;
import com.Euflausino.auth.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component //entende como um componente generico
public class SecurityFilter extends OncePerRequestFilter {//herda uma classe do spring que implementa a classe filter
    //posso ter varios filtros por aplicacoes
    // esse filtro executa uma vez por request

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    SecurityFilter(TokenService tokenService, UsuarioRepository repository) {
        this.tokenService = tokenService;
        this.usuarioRepository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain ) throws ServletException, IOException {

        String tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            String getSubject = tokenService.getSubject(tokenJWT);
            var usuario = usuarioRepository.findByUsername(getSubject);
            var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.get().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

       filterChain.doFilter(request,response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String authReader = request.getHeader("Authorization");
        if (authReader != null) {
            return authReader.replace("Bearer ", "");
        }
        return null;
    }


}
