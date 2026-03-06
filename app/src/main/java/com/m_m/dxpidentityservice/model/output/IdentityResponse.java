package com.m_m.dxpidentityservice.model.output;

import java.time.LocalDateTime;
import java.util.UUID;

public record IdentityResponse(
        UUID usuarioId,
        String nomeUsuario,
        String emailUsuario,
        Boolean ativo,
        LocalDateTime dataHoraCriacao
) {}
