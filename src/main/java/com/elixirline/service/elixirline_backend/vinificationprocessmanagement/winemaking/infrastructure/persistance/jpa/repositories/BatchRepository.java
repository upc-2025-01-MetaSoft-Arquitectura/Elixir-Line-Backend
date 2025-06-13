package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.infrastructure.persistance.jpa.repositories;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.BatchVineyard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BatchRepository extends JpaRepository<BatchVineyard, Long> {
    List<BatchVineyard> findByVineyardCode(String vineyardCode);
}
