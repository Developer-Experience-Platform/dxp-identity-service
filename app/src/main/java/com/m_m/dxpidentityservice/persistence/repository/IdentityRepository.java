package com.m_m.dxpidentityservice.persistence.repository;

import com.m_m.dxpidentityservice.persistence.entity.IdentityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IdentityRepository extends JpaRepository<IdentityEntity, UUID> {
}
