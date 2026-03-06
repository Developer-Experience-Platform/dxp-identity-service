package com.m_m.dxpidentityservice.mapper;

import com.m_m.dxpidentityservice.model.input.IdentityInput;
import com.m_m.dxpidentityservice.model.output.IdentityResponse;
import com.m_m.dxpidentityservice.persistence.entity.IdentityEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IdentityMapper {

    IdentityEntity toEntity(IdentityInput request);

    IdentityResponse toResponse(IdentityInput request);

    List<IdentityResponse> toResponseList(List<IdentityInput> request);
}
