package com.petar.workspring.repository;

import com.petar.workspring.domain.entities.PartnersLoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnersRepository extends JpaRepository<PartnersLoginEntity, Integer> {
}
