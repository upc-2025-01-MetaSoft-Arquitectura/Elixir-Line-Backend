package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.domain.model.exceptions;

public class DuplicateCampaignNameException extends RuntimeException {
    public DuplicateCampaignNameException(String message) {
        super(message);
    }
}
