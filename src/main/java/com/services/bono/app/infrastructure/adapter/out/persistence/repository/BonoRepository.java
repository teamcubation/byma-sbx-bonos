package com.services.bono.app.infrastructure.adapter.out.persistence.repository;

import com.services.bono.app.infrastructure.adapter.out.persistence.entity.BonoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonoRepository extends JpaRepository<BonoEntity, Long> {
}
