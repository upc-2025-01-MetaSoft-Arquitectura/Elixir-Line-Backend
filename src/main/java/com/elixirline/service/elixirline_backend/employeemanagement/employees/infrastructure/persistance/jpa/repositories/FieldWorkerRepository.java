package com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldWorkerRepository extends JpaRepository<FieldWorker, Long> {
    List<FieldWorker> findByVinegrowerId(Long vinegrowerId);
}
