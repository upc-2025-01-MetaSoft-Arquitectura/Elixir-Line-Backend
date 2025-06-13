package com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.Winegrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinegrowerRepository extends JpaRepository<Winegrower, Long> {

}
