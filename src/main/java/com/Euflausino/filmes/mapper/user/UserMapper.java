package com.Euflausino.filmes.mapper.user;


import com.Euflausino.filmes.dto.user.TokenResponseDTO;
import com.Euflausino.filmes.dto.user.UserCadastroDTO;
import com.Euflausino.filmes.dto.user.UserResponseDTO;
import com.Euflausino.filmes.entities.user.User;

public class UserMapper {

    public static User mapToEntity(UserCadastroDTO user) {
        return new User(
                user.username(),
                user.senha(),
                user.email()
        );
    }

    public static UserResponseDTO mapToDTO(User user) {
        return new UserResponseDTO(
                user.getUsername(),
                user.getEmail()
        );
    }

    public static TokenResponseDTO mapToken(String token){
        return new TokenResponseDTO(token);
    }

}
