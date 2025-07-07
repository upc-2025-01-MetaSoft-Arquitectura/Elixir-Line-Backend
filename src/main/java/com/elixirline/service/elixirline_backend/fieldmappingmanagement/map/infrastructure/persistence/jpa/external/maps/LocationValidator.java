package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.infrastructure.persistence.jpa.external.maps;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class LocationValidator {
    private final GeocodingService geocodingService;

    public LocationValidator(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    public boolean estaEnPeru(double lat, double lng) {
        try {
            String response = geocodingService.obtenerUbicacion(lat, lng);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode results = root.path("results");

            for (JsonNode result : results) {
                JsonNode components = result.path("address_components");

                for (JsonNode comp : components) {
                    String name = comp.path("long_name").asText();
                    JsonNode types = comp.path("types");

                    if (types.isArray()) {
                        for (JsonNode type : types) {
                            if ("country".equals(type.asText())) {
                                if (name.toLowerCase().contains("peru")) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
