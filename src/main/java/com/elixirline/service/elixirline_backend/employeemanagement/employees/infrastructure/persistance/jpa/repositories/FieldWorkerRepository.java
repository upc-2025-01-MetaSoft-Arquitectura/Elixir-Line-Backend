package com.elixirline.service.elixirline_backend.employeemanagement.employees.infrastructure.persistance.jpa.repositories;

import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.aggregates.FieldWorker;
import com.elixirline.service.elixirline_backend.employeemanagement.employees.domain.model.valueobjects.EmployeeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldWorkerRepository extends JpaRepository<FieldWorker, Long> {
    List<FieldWorker> findByStatus(EmployeeStatus status);

    default List<FieldWorker> findAllActive() {
        return findByStatus(EmployeeStatus.ACTIVE);
    }

<<<<<<< HEAD
<<<<<<< HEAD
    List<FieldWorker> findByVinegrowerId(Long vinegrowerId);
    List<FieldWorker> findByVinegrowerIdAndStatus(Long vinegrowerId, EmployeeStatus status);
=======
    List<FieldWorker> findByWinegrowerId(Long vinegrowerId);
    List<FieldWorker> findByWinegrowerIdAndStatus(Long vinegrowerId, EmployeeStatus status);
>>>>>>> develop
=======
    List<FieldWorker> findByWinegrowerId(Long vinegrowerId);
    List<FieldWorker> findByWinegrowerIdAndStatus(Long vinegrowerId, EmployeeStatus status);
>>>>>>> feature/winemakingprocess
}
