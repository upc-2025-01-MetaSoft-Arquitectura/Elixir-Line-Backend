package com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.transform.processstage;

import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.aggregates.*;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.domain.model.entities.ProcessStage;
import com.elixirline.service.elixirline_backend.vinificationprocessmanagement.winemaking.interfaces.rest.resources.processstage.*;
import org.springframework.stereotype.Component;

@Component
public class ProcessStageResourceAssembler {
    public static ProcessStageResource toResource(ProcessStage stage) {
        if (stage == null) {
            return null;
        }

        ProcessStageResource resource;

        //TODO: Debo considerar usar el patrón de diseño FACTORY METHOD o VISITOR para mapear mejor las etapas.
        if (stage instanceof ReceptionStage receptionStage) {
            ProcessReceptionStageResource receptionResource = new ProcessReceptionStageResource();
            receptionResource.setSugarLevel(receptionStage.getSugarLevel());
            receptionResource.setPHLevel(receptionStage.getPHLevel());
            receptionResource.setTemperature(receptionStage.getTemperature());
            receptionResource.setQuantityKg(receptionStage.getQuantityKg());
            receptionResource.setCurrentStage(receptionStage.getCurrentStage());
            resource = receptionResource;
        }
        else if (stage instanceof CorrectionStage correctionStage) {
            ProcessCorrectionStageResource correctionResource = new ProcessCorrectionStageResource();
            correctionResource.setInitialSugarLevel(correctionStage.getInitialSugarLevel());
            correctionResource.setFinalSugarLevel(correctionStage.getFinalSugarLevel());
            correctionResource.setAddedSugar(correctionStage.getAddedSugar());
            correctionResource.setInitialPH(correctionStage.getInitialPH());
            correctionResource.setFinalPH(correctionStage.getFinalPH());
            correctionResource.setAcidType(correctionStage.getAcidType());
            correctionResource.setAddedAcid(correctionStage.getAddedAcid());
            correctionResource.setAddedSulphites(correctionStage.getAddedSulphites());
            correctionResource.setNutrients(correctionStage.getNutrients());
            correctionResource.setJustification(correctionStage.getJustification());
            correctionResource.setCurrentStage(correctionStage.getCurrentStage());
            resource = correctionResource;
        }
        else if (stage instanceof FermentationStage fermentationStage) {
            ProcessFermentationStageResource fermentationResource = new ProcessFermentationStageResource();
            fermentationResource.setYeastUsed(fermentationStage.getYeastUsed());
            fermentationResource.setFermentationType(fermentationStage.getFermentationType());
            fermentationResource.setInitialSugarLevel(fermentationStage.getInitialSugarLevel());
            fermentationResource.setFinalSugarLevel(fermentationStage.getFinalSugarLevel());
            fermentationResource.setInitialPH(fermentationStage.getInitialPH());
            fermentationResource.setFinalPH(fermentationStage.getFinalPH());
            fermentationResource.setMaxTemperature(fermentationStage.getMaxTemperature());
            fermentationResource.setMinTemperature(fermentationStage.getMinTemperature());
            fermentationResource.setTankCode(fermentationStage.getTankCode());
            fermentationResource.setCurrentStage(fermentationStage.getCurrentStage());
            resource = fermentationResource;
        }
        else if (stage instanceof PressingStage pressingStage) {
            ProcessPressingStageResource pressingResource = new ProcessPressingStageResource();
            pressingResource.setPressType(pressingStage.getPressType());
            pressingResource.setPressure(pressingStage.getPressure());
            pressingResource.setDuration(pressingStage.getDuration());
            pressingResource.setPomadeWeight(pressingStage.getPomadeWeight());
            pressingResource.setYield(pressingStage.getYield());
            pressingResource.setMustUsage(pressingStage.getMustUsage());
            pressingResource.setCurrentStage(pressingStage.getCurrentStage());
            resource = pressingResource;           
        }
        else if (stage instanceof ClarificationStage clarificationStage) {
            ProcessClarificationStageResource clarificationResource = new ProcessClarificationStageResource();
            clarificationResource.setMethodUsed(clarificationStage.getMethodUsed());
            clarificationResource.setInitialTurbidity(clarificationStage.getInitialTurbidity());
            clarificationResource.setFinalTurbidity(clarificationStage.getFinalTurbidity());
            clarificationResource.setVolume(clarificationStage.getVolume());
            clarificationResource.setTemperature(clarificationStage.getTemperature());
            clarificationResource.setDuration(clarificationStage.getDuration());
            clarificationResource.setClarifyingAgents(clarificationStage.getClarifyingAgents());
            clarificationResource.setCurrentStage(clarificationStage.getCurrentStage());
            resource = clarificationResource;
        }
        else if (stage instanceof AgingStage agingStage) {
            ProcessAgingStageResource agingResource = new ProcessAgingStageResource();
            agingResource.setContainerType(agingStage.getContainerType());
            agingResource.setMaterial(agingStage.getMaterial());
            agingResource.setContainerCode(agingStage.getContainerCode());
            agingResource.setAverageTemperature(agingStage.getAverageTemperature());
            agingResource.setVolume(agingStage.getVolume());
            agingResource.setDuration(agingStage.getDuration());
            agingResource.setFrequency(agingStage.getFrequency());
            agingResource.setBatonnage(agingStage.getBatonnage());
            agingResource.setRefills(agingStage.getRefills());
            agingResource.setRackings(agingStage.getRackings());
            agingResource.setPurpose(agingStage.getPurpose());
            agingResource.setCurrentStage(agingStage.getCurrentStage());
            resource = agingResource;
        }
        else if (stage instanceof FiltrationStage filtrationStage) {
            ProcessFiltrationStageResource filtrationResource = new ProcessFiltrationStageResource();
            filtrationResource.setFilterType(filtrationStage.getFilterType());
            filtrationResource.setFilterMedium(filtrationStage.getFilterMedium());
            filtrationResource.setPorosity(filtrationStage.getPorosity());
            filtrationResource.setInitialTurbidity(filtrationStage.getInitialTurbidity());
            filtrationResource.setFinalTurbidity(filtrationStage.getFinalTurbidity());
            filtrationResource.setTemperature(filtrationStage.getTemperature());
            filtrationResource.setPressure(filtrationStage.getPressure());
            filtrationResource.setFilteredVolume(filtrationStage.getFilteredVolume());
            filtrationResource.setSterileFiltration(filtrationStage.getSterileFiltration());
            filtrationResource.setChangedFiltration(filtrationStage.getChangedFiltration());
            filtrationResource.setChangeReason(filtrationStage.getChangeReason());
            filtrationResource.setCurrentStage(filtrationStage.getCurrentStage());
            resource = filtrationResource;
        }
        else if (stage instanceof BottlingStage bottlingStage) {
            ProcessBottlingStageResource bottlingResource = new ProcessBottlingStageResource();
            bottlingResource.setBottlingLine(bottlingStage.getBottlingLine());
            bottlingResource.setFilledBottles(bottlingStage.getFilledBottles());
            bottlingResource.setBottleVolume(bottlingStage.getBottleVolume());
            bottlingResource.setTotalVolume(bottlingStage.getTotalVolume());
            bottlingResource.setSealingType(bottlingStage.getSealingType());
            bottlingResource.setVineyardCode(bottlingStage.getVineyardCode());
            bottlingResource.setTemperature(bottlingStage.getTemperature());
            bottlingResource.setFilteredBeforeBottling(bottlingStage.getFilteredBeforeBottling());
            bottlingResource.setLabelsAtThisStage(bottlingStage.getLabelsAtThisStage());
            bottlingResource.setCapsuleOrSealApplication(bottlingStage.getCapsuleOrSealApplication());
            bottlingResource.setCurrentStage(bottlingStage.getCurrentStage());
            resource = bottlingResource;
        }
        else {
            throw new IllegalArgumentException("Tipo de etapa no soportado: " + stage.getClass().getName());
        }

        resource.setId(stage.getId());
        resource.setBatchId(stage.getBatchId());
        resource.setEmployee(stage.getEmployee());
        resource.setStartDate(stage.getStartDate());
        resource.setEndDate(stage.getEndDate());
        resource.setComment(stage.getComment());
        resource.setCompletionStatus(stage.getCompletionStatus()); 
        resource.setCompletedAt(stage.getCompletedAt());

        return resource;
    }
}

