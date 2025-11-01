package com.Euflausino.auth.mapper;


import com.Euflausino.auth.dto.TokenResponseDTO;
import com.Euflausino.auth.dto.UserCadastroDTO;
import com.Euflausino.auth.dto.UserResponseDTO;
import com.Euflausino.auth.entity.User;

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
