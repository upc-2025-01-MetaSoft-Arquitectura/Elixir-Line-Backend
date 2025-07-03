package com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.infrastructure.persistance.jpa.repositories;

import com.elixirline.service.elixirline_backend.agriculturalactivitiesmanagement.tasks.domain.model.aggregates.Inputs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InputsRepository extends JpaRepository<Inputs, Long> {
    Optional<Inputs> findByNameIgnoreCase(String name);
    List<Inputs> findByWinegrowerId(Long winegrowerId);
}
