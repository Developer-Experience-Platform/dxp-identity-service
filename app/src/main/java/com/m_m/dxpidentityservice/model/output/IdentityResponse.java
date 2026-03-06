package com.m_m.dxpidentityservice.model.output;

import java.time.LocalDateTime;
import java.util.UUID;

public record IdentityResponse(
        UUID idUsuario,
        String nomeUsuario,
        String emailUsuario,
        Boolean ativo,
        LocalDateTime dataHoraCriacao
) {}
