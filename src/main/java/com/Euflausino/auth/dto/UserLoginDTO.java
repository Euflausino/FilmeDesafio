package com.Euflausino.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(
        @NotBlank
        String username,
        @NotBlank
        String senha

) {
}
