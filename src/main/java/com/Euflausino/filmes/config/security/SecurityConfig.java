package com.Euflausino.filmes.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //indica que vamos personalizar a segurança.
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)//desabilita a proteção de csrf por que o token já é a proteção.
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // habilita políticas de sessão (stateless JWT, por ex.)
                .formLogin(AbstractHttpConfigurer::disable)//desabilita o formulario de login
                //.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                //definindo autorizacao endpoint (posso ter endpoints publicos)
                .build(); //retorna o filtro de segurança
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager(); //bean para autenticar usuario e senha
    }

    @Bean // cria objeto gerenciado pelo spring
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // bean para encriptar senha
    }

}
