package com.Euflausino.filmes.repository;

import com.Euflausino.filmes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByUsername(String username);
}
