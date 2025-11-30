package pe.iotteam.plantcare.plant.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorData {
    @JsonProperty("airTemperatureC")
    private Double airTemperatureC;
    
    @JsonProperty("airHumidityPct")
    private Double airHumidityPct;
    
    @JsonProperty("lightIntensityLux")
    private Integer lightIntensityLux;
    
    @JsonProperty("soilMoisturePct")
    private Integer soilMoisturePct;
    
    @JsonProperty("deviceId")
    private String deviceId;
    
    @JsonProperty("timestamp")
    private LocalDateTime timestamp;
    
    // Métodos de compatibilidad para mantener el código existente
    public Integer getTemperature() {
        return airTemperatureC != null ? airTemperatureC.intValue() : null;
    }
    
    public Double getHumidity() {
        return airHumidityPct;
    }
    
    public Integer getLight() {
        return lightIntensityLux;
    }
    
    public Integer getSoilHumidity() {
        return soilMoisturePct;
    }
}