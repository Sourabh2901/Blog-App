package com.sr.externalApiIntegration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalApiRepo extends JpaRepository<ExternalApiEntity, Integer>{
}
