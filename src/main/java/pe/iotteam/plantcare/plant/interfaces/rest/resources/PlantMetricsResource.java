package pe.iotteam.plantcare.plant.interfaces.rest.resources;

import java.time.LocalDateTime;

public record PlantMetricsResource(
        Long id,
        Long plantId,
        String deviceId,
        Double airTemperatureC,
        Double airHumidityPct,
        Integer lightIntensityLux,
        Integer soilMoisturePct,
        LocalDateTime timestamp
) {
}