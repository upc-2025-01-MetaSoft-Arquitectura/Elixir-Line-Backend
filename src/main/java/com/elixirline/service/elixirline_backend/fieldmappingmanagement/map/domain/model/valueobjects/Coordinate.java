package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {
    private double lat;
    private double lng;

//    public Coordinate(double lat, double lng) {
//        if (lat < -90 || lat > 90) {
//            throw new IllegalArgumentException("Latitude must be between -90 and 90.");
//        }
//        if (lng < -180 || lng > 180) {
//            throw new IllegalArgumentException("Longitude must be between -180 and 180.");
//        }
//        this.lat = lat;
//        this.lng = lng;
//    }
}
