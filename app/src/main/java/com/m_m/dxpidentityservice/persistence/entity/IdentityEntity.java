package com.m_m.dxpidentityservice.persistence.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "usuario_identity_db")
public class IdentityEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "id_usuario", length = 36, nullable = false, unique = true)
    private UUID idUsuario;

    @Column(name = "nome_usuario", length = 255, nullable = false)
    private String nomeUsuario;

    @Column(name = "email_usuario", length = 255, nullable = false, unique = true)
    private String emailUsuario;

    @Column(name = "senha_usuario", length = 255, nullable = false)
    private String senha;

    @Column(name = "ativo_usuario", nullable = false)
    private Boolean ativo;

    @Column(name = "data_criacao_usuario", nullable = false)
    private LocalDateTime dataHoraCriacao;

}
