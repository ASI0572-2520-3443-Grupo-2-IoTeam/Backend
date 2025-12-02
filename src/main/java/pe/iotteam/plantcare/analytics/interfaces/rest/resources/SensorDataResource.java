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
        
        @JsonProperty("device_id")
        String deviceId,
        
        @JsonProperty("air_temperature_celsius")
        Double airTemperatureCelsius,
        
        @JsonProperty("air_humidity_percent")
        Double airHumidityPercent,
        
        @JsonProperty("luminosity_lux")
        Integer luminosityLux,
        
        @JsonProperty("soil_moisture_percent")
        Integer soilMoisturePercent,
        
        @JsonProperty("created_at")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime createdAt
) {
}
