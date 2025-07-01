package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects.BlockNumber;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects.ContractAddress;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects.ContractName;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects.DeployedAt;
import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "deployed_contracts")
@NoArgsConstructor
public class DeployedContract extends AuditableAbstractAggregateRoot<DeployedContract>  {
    @Embedded
    private ContractName contractName;

    @Embedded
    private ContractAddress contractAddress;

    @Embedded
    private DeployedAt deployedAt;

    @Embedded
    private BlockNumber blockNumber;


    public DeployedContract(ContractName contractName, ContractAddress contractAddress, DeployedAt deployedAt, BlockNumber blockNumber) {
        this.contractName = contractName;
        this.contractAddress = contractAddress;
        this.deployedAt = deployedAt;
        this.blockNumber = blockNumber;
    }
}
