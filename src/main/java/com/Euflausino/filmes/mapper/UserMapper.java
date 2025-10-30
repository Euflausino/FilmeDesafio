package com.Euflausino.filmes.mapper;


import com.Euflausino.filmes.dto.UserCadastroDTO;
import com.Euflausino.filmes.dto.UserResponseDTO;
import com.Euflausino.filmes.entities.User;

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

}
