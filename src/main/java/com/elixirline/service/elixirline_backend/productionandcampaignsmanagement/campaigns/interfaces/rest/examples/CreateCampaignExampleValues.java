package com.elixirline.service.elixirline_backend.productionandcampaignsmanagement.campaigns.interfaces.rest.examples;

public class CreateCampaignExampleValues {
    public static final String EXAMPLE_COMPLETE = """
        {
          "name": "Campaña Invierno 2025",
          "age": "2025",
          "driverUserId": 1,
          "lots": 12,
          "status": "EN_PROCESO",
          "startDate": "2025-06-01",
          "endDate": "2025-06-10"
        }
        """;

    public static final String EXAMPLE_MINIMAL = """
        {
          "name": "Campaña Invierno 2025",
          "age": "2025",
          "driverUserId": 1,
          "startDate": "2025-06-01",
          "endDate": "2025-06-10"
        }
        """;
}
