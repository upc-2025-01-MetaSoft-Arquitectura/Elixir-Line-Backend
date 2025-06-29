package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.services;

import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetStageDetails;
import com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.queries.GetTransactionInfo;
import java.util.Optional;

public interface BlockchainTransactionQueryService {
    Optional<GetTransactionInfo> getStageSignedTransaction(GetStageDetails query) throws Exception;
}
