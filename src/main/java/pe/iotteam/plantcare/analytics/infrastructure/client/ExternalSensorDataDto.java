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
    
    @JsonProperty("created_at")
    @JsonDeserialize(using = Rfc1123DateTimeDeserializer.class)
    private LocalDateTime createdAt;
    
    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("air_temperature_celsius")
    private Double airTemperatureCelsius;

    @JsonProperty("air_humidity_percent")
    private Double airHumidityPercent;

    @JsonProperty("luminosity_lux")
    private Integer luminosityLux;

    @JsonProperty("soil_moisture_percent")
    private Integer soilMoisturePercent;
}