package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.infrastructure.persistence.jpa.external.maps;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GeocodingService {
    @Value("${google.maps.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public String obtenerUbicacion(double lat, double lng) {
        String url = UriComponentsBuilder.fromHttpUrl("https://maps.googleapis.com/maps/api/geocode/json")
                .queryParam("latlng", lat + "," + lng)
                .queryParam("key", apiKey)
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
}
