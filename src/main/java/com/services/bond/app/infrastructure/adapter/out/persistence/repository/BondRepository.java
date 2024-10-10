package com.services.bond.app.infrastructure.adapter.out.persistence.repository;

import com.services.bond.app.infrastructure.adapter.out.persistence.entity.BondEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BondRepository extends JpaRepository<BondEntity, Long> {
}
