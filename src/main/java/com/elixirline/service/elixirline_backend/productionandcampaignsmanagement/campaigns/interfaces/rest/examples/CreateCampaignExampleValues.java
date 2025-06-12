package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.examples;

public class CreateCampaignExampleValues {
    public static final String EXAMPLE_COMPLETE = """
        {
          "name": "Campaña Invierno 2025",
          "year": "2025",
          "winegrowerId": 1,
          "batches": 12,
          "status": "EN_PROCESO",
          "startDate": "2025-06-01",
          "endDate": "2025-06-10"
        }
        """;

    public static final String EXAMPLE_MINIMAL = """
        {
          "name": "Campaña Invierno 2025",
          "year": "2025",
          "winegrowerId": 1,
          "startDate": "2025-06-01",
          "endDate": "2025-06-10"
        }
        """;
}
