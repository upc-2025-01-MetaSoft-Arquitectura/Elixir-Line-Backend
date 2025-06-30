package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.infrastructure.persistance.jpa.repositories;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.aggregates.DeployedContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeployedContractRepository extends JpaRepository<DeployedContract, Long> {
    Optional<DeployedContract> findTopByContractNameContractNameOrderByDeployedAtDesc(String contractName);
}
