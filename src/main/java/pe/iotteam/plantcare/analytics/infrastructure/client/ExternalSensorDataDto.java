package pe.iotteam.plantcare.analytics.infrastructure.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for sensor data from external Edge Service API / Wokwi
 * Matches the JSON structure returned by the API
 * Uses same naming convention as Plants BC
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExternalSensorDataDto {
    
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;
    
    @JsonProperty("deviceId")
    private String deviceId;

    @JsonProperty("airTemperatureC")
    private Double airTemperatureC;

    @JsonProperty("airHumidityPct")
    private Double airHumidityPct;

    @JsonProperty("lightIntensityLux")
    private Integer lightIntensityLux;

    @JsonProperty("soilMoisturePct")
    private Integer soilMoisturePct;
}