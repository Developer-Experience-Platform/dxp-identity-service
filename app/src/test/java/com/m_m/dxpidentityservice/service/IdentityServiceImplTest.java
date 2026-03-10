package com.m_m.dxpidentityservice.service;

import com.m_m.dxpidentityservice.build.IdentityInputBuilder;
import com.m_m.dxpidentityservice.mapper.IdentityMapper;
import com.m_m.dxpidentityservice.model.input.IdentityInput;
import com.m_m.dxpidentityservice.model.output.IdentityResponse;
import com.m_m.dxpidentityservice.persistence.entity.IdentityEntity;
import com.m_m.dxpidentityservice.persistence.repository.IdentityRepository;
import com.m_m.dxpidentityservice.service.impl.IdentityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("IdentityServiceImpl Tests")
public class IdentityServiceImplTest {

    @Mock
    private IdentityRepository identityRepository;

    @Mock
    private IdentityMapper identityMapper;

    @InjectMocks
    private IdentityServiceImpl identityService;

    private IdentityInput identityInput;
    private IdentityEntity identityEntity;
    private IdentityResponse identityResponse;
    private UUID testUUID;

    @BeforeEach
    public void setUp() {
        testUUID = UUID.randomUUID();

        identityInput = IdentityInputBuilder.aIdentityInput()
                .withNomeUsuario("João Silva")
                .withEmailUsuario("joao@example.com")
                .withSenha("senha123")
                .withAtivo(true)
                .build();

        identityEntity = new IdentityEntity();
        identityEntity.setIdUsuario(testUUID);
        identityEntity.setNomeUsuario("João Silva");
        identityEntity.setEmailUsuario("joao@example.com");
        identityEntity.setSenha("senha123");
        identityEntity.setAtivo(true);
        identityEntity.setDataHoraCriacao(LocalDateTime.now());

        identityResponse = new IdentityResponse(
                testUUID,
                "João Silva",
                "joao@example.com",
                true,
                LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("Deve criar uma identidade corretamente")
    public void deveCriarIdentityCorretamente() {
        when(identityMapper.toEntity(identityInput)).thenReturn(identityEntity);
        when(identityRepository.save(any(IdentityEntity.class))).thenReturn(identityEntity);
        when(identityMapper.toResponse(identityEntity)).thenReturn(identityResponse);

        IdentityResponse result = identityService.createIdentity(identityInput);

        assertNotNull(result);
        assertEquals("João Silva", result.nomeUsuario());
        assertEquals("joao@example.com", result.emailUsuario());
        assertTrue(result.ativo());

        verify(identityMapper, times(1)).toEntity(identityInput);
        verify(identityRepository, times(1)).save(any(IdentityEntity.class));
        verify(identityMapper, times(1)).toResponse(identityEntity);
    }

    @Test
    @DisplayName("Deve buscar identidade por ID com sucesso")
    public void deveBuscarIdentidadePorIdComSucesso() {
        when(identityRepository.findById(testUUID)).thenReturn(Optional.of(identityEntity));
        when(identityMapper.toResponse(identityEntity)).thenReturn(identityResponse);

        IdentityResponse result = identityService.getIdentityById(testUUID);

        assertNotNull(result);
        assertEquals("João Silva", result.nomeUsuario());
        assertEquals("joao@example.com", result.emailUsuario());

        verify(identityRepository, times(1)).findById(testUUID);
        verify(identityMapper, times(1)).toResponse(identityEntity);
    }

    @Test
    @DisplayName("Deve lançar exceção quando identidade não é encontrada")
    public void deveLancarExcecaoQuandoIdentidadeNaoEncontrada() {
        // Arrange
        when(identityRepository.findById(testUUID)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> identityService.getIdentityById(testUUID));

        verify(identityRepository, times(1)).findById(testUUID);
    }

    @Test
    @DisplayName("Deve retornar lista de identidades")
    public void deveRetornarListaDeIdentidades() {
        IdentityEntity entity2 = new IdentityEntity();
        entity2.setIdUsuario(UUID.randomUUID());
        entity2.setNomeUsuario("Maria Santos");
        entity2.setEmailUsuario("maria@example.com");

        List<IdentityEntity> entities = List.of(identityEntity, entity2);
        List<IdentityResponse> responses = List.of(identityResponse);

        when(identityRepository.findAll()).thenReturn(entities);
        when(identityMapper.toResponseList(entities)).thenReturn(responses);

        List<IdentityResponse> result = identityService.getIdentities();

        assertNotNull(result);
        assertEquals(1, result.size());

        verify(identityRepository, times(1)).findAll();
        verify(identityMapper, times(1)).toResponseList(entities);
    }

    @Test
    @DisplayName("Deve atualizar identidade corretamente")
    public void deveAtualizarIdentidadeCorretamente() {
        IdentityInput updateInput = IdentityInputBuilder.aIdentityInput()
                .withNomeUsuario("João Silva Atualizado")
                .withEmailUsuario("joao.atualizado@example.com")
                .withSenha("novasenha123")
                .withAtivo(false)
                .build();

        IdentityEntity updatedEntity = new IdentityEntity();
        updatedEntity.setIdUsuario(testUUID);
        updatedEntity.setNomeUsuario("João Silva Atualizado");

        when(identityRepository.findById(testUUID)).thenReturn(Optional.of(identityEntity));
        when(identityMapper.updateEntityFromInput(updateInput, identityEntity)).thenReturn(updatedEntity);
        when(identityRepository.save(updatedEntity)).thenReturn(updatedEntity);

        identityService.updateIdentity(updateInput, testUUID);

        verify(identityRepository, times(1)).findById(testUUID);
        verify(identityMapper, times(1)).updateEntityFromInput(updateInput, identityEntity);
        verify(identityRepository, times(1)).save(updatedEntity);
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar identidade que não existe")
    public void deveLancarExcecaoAoAtualizarIdentidadeNaoExistente() {
        when(identityRepository.findById(testUUID)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> identityService.updateIdentity(identityInput, testUUID));

        verify(identityRepository, times(1)).findById(testUUID);
    }
}


