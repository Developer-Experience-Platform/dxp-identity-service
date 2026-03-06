package com.m_m.dxpidentityservice.service.impl;

import com.m_m.dxpidentityservice.mapper.IdentityMapper;
import com.m_m.dxpidentityservice.model.input.IdentityInput;
import com.m_m.dxpidentityservice.model.output.IdentityResponse;
import com.m_m.dxpidentityservice.persistence.repository.IdentityRepository;
import com.m_m.dxpidentityservice.service.IdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class IdentityServiceImpl implements IdentityService {


    private final IdentityRepository identityRepository;
    private final IdentityMapper mapper;

    @Override
    public IdentityResponse createIdentity(IdentityInput request) {
        var identityEntity = mapper.toEntity(request);
        identityRepository.save(identityEntity);
        return mapper.toResponse(request);
    }
}
