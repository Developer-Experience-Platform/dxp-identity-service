package com.m_m.dxpidentityservice.service.impl;

import com.m_m.dxpidentityservice.mapper.IdentityMapper;
import com.m_m.dxpidentityservice.model.input.IdentityInput;
import com.m_m.dxpidentityservice.model.output.IdentityResponse;
import com.m_m.dxpidentityservice.persistence.repository.IdentityRepository;
import com.m_m.dxpidentityservice.service.IdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class IdentityServiceImpl implements IdentityService {


    private final IdentityRepository identityRepository;
    private final IdentityMapper mapper;

    @Override
    public IdentityResponse createIdentity(IdentityInput request) {
        var identityEntity = mapper.toEntity(request);
        var saved=  identityRepository.save(identityEntity);
        return mapper.toResponse(saved);
    }

    @Override
    public IdentityResponse getIdentityById(UUID id) {
        var identityEntity = identityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Identity not found with id: " + id));
        return mapper.toResponse(identityEntity);
    }

    @Override
    public List<IdentityResponse> getIdentities() {
        var identityEntities = identityRepository.findAll();
        return mapper.toResponseList(identityEntities);
    }

    @Override
    public void updateIdentity(IdentityInput request, UUID id) {
         var existingEntity = identityRepository.findById(id).orElseThrow(() -> new RuntimeException("Identity not found with id: " + id));
         var updatedEntity = mapper.updateEntityFromInput(request, existingEntity);
         identityRepository.save(updatedEntity);
    }
}
