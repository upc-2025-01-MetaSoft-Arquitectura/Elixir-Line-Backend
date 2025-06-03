package com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Vinegrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinegrowerRepository extends JpaRepository<Vinegrower, Long> {

}
