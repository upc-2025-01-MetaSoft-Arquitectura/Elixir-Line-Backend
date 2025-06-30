package com.elixirline.service.elixirline_backend.blockchain.smartcontracts.domain.model.valueobjects;

public enum VinificationStage {
    RECEPTION("RECEPTION"),
    CORRECTION("CORRECTION"),
    FERMENTATION("FERMENTATION"),
    PRESSING("PRESSING"),
    CLARIFICATION("CLARIFICATION"),
    AGING("AGING"),
    FILTRATION("FILTRATION"),
    BOTTLING("BOTTLING");

    private final String stageName;

    VinificationStage(String stageName) {
        this.stageName = stageName;
    }

    public String getStageName() {
        return stageName;
    }

    public static VinificationStage fromString(String text) {
        for (VinificationStage stage : VinificationStage.values()) {
            if (stage.stageName.equalsIgnoreCase(text)) {
                return stage;
            }
        }
        throw new IllegalArgumentException("No enum constant for stage: " + text);
    }
}
