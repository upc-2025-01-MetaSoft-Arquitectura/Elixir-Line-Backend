package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.infrastructure.persistence.jpa.repositories;

import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.aggregates.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlotRepository extends JpaRepository<Plot, Long> {
    List<Plot> findByWinegrowerId(Long winegrowerId);
}
