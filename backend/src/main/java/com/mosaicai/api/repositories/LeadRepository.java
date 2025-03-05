package com.mosaicai.api.repositories;

import com.mosaicai.api.entities.LeadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LeadRepository extends JpaRepository<LeadEntity, UUID> {
}
