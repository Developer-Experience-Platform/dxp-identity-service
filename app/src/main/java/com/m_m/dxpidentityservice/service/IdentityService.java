package com.m_m.dxpidentityservice.service;

import com.m_m.dxpidentityservice.model.input.IdentityInput;
import com.m_m.dxpidentityservice.model.output.IdentityResponse;

public interface IdentityService {

    IdentityResponse createIdentity(IdentityInput request);
}
