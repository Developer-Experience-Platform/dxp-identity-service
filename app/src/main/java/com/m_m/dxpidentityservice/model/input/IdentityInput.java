package com.m_m.dxpidentityservice.model.input;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class IdentityInput {

    private String nomeUsuario;
    @Email
    private String emailUsuario;
    private String senha;
    private Boolean ativo;
}
