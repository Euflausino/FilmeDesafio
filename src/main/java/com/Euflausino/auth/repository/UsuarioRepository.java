package com.Euflausino.auth.repository;

import com.Euflausino.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByUsername(String username);
}
