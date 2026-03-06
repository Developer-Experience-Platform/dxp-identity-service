package com.m_m.dxpidentityservice.service;

import com.m_m.dxpidentityservice.model.input.IdentityInput;
import com.m_m.dxpidentityservice.model.output.IdentityResponse;

import java.util.UUID;

public interface IdentityService {

    IdentityResponse createIdentity(IdentityInput request);

    IdentityResponse getIdentityById(UUID id);
}
