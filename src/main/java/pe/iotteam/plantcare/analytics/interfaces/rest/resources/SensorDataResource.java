package pe.iotteam.plantcare.analytics.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * Resource DTO for sensor data record responses
 */
public record SensorDataResource(
        @JsonProperty("id")
        Long id,
        
        @JsonProperty("deviceId")
        String deviceId,
        
        @JsonProperty("airTemperatureC")
        Double airTemperatureC,
        
        @JsonProperty("airHumidityPct")
        Double airHumidityPct,
        
        @JsonProperty("lightIntensityLux")
        Integer lightIntensityLux,
        
        @JsonProperty("soilMoisturePct")
        Integer soilMoisturePct,
        
        @JsonProperty("timestamp")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime timestamp
) {
}
