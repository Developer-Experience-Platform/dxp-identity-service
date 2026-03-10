package com.m_m.dxpidentityservice.build;

import com.m_m.dxpidentityservice.model.input.IdentityInput;

public class IdentityInputBuilder {

    private String nomeUsuario;
    private String emailUsuario;
    private String senha;
    private Boolean ativo;

    public IdentityInputBuilder withNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
        return this;
    }

    public IdentityInputBuilder withEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
        return this;
    }

    public IdentityInputBuilder withSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public IdentityInputBuilder withAtivo(Boolean ativo) {
        this.ativo = ativo;
        return this;
    }

    public IdentityInput build() {
        IdentityInput identityInput = new IdentityInput();
        identityInput.setNomeUsuario(nomeUsuario);
        identityInput.setEmailUsuario(emailUsuario);
        identityInput.setSenha(senha);
        identityInput.setAtivo(ativo);
        return identityInput;
    }

    public static IdentityInputBuilder aIdentityInput() {
        return new IdentityInputBuilder();
    }
}
