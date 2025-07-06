package com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.aggregates;

import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.valueobjects.Coordinate;
import com.elixirline.service.elixirline_backend.fieldmappingmanagement.map.domain.model.valueobjects.PlotType;
import com.elixirline.service.elixirline_backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Plot extends AuditableAbstractAggregateRoot<Plot> {
    private PlotType type;
    @ElementCollection
    private List<Coordinate> path;
    private String label;
    private Long wineBatchId;

    public Plot(PlotType type, List<Coordinate> path, String label, Long wineBatchId) {
        this.type = type;
        this.path = path;
        this.label = label;
        this.wineBatchId = wineBatchId;
    }
    public void updateInformation(PlotType type, List<Coordinate> path, String label, Long wineBatchId) {
        if (type != null) this.type = type;
        if (path != null) this.path = path;
        if (label != null) this.label = label;
        if (wineBatchId != null) this.wineBatchId = wineBatchId;
    }
}
