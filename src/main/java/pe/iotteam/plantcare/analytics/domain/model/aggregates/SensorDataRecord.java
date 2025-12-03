package pe.iotteam.plantcare.analytics.domain.model.aggregates;

import lombok.Getter;
import pe.iotteam.plantcare.analytics.domain.model.valueobjects.SensorDataId;

import java.time.LocalDateTime;

/**
 * SensorDataRecord aggregate root
 * Represents a sensor data record from the external Edge Service API
 */
@Getter
public class SensorDataRecord {
    private SensorDataId id;
    private String deviceId;
    private Double airTemperatureC;
    private Double airHumidityPct;
    private Integer lightIntensityLux;
    private Integer soilMoisturePct;
    private LocalDateTime timestamp;

    // Constructor for new records from API
    public SensorDataRecord(String deviceId, Double airTemperatureC, Double airHumidityPct, 
                           Integer lightIntensityLux, Integer soilMoisturePct, LocalDateTime timestamp) {
        this.deviceId = deviceId;
        this.airTemperatureC = airTemperatureC;
        this.airHumidityPct = airHumidityPct;
        this.lightIntensityLux = lightIntensityLux;
        this.soilMoisturePct = soilMoisturePct;
        this.timestamp = timestamp;
    }

    // Constructor for persisted records
    public SensorDataRecord(SensorDataId id, String deviceId, Double airTemperatureC, 
                           Double airHumidityPct, Integer lightIntensityLux, Integer soilMoisturePct, 
                           LocalDateTime timestamp) {
        this.id = id;
        this.deviceId = deviceId;
        this.airTemperatureC = airTemperatureC;
        this.airHumidityPct = airHumidityPct;
        this.lightIntensityLux = lightIntensityLux;
        this.soilMoisturePct = soilMoisturePct;
        this.timestamp = timestamp;
    }

    /**
     * Creates a unique identifier for this record based on device_id and timestamp
     * This is used to detect duplicates during ingestion
     */
    public String getUniqueIdentifier() {
        return deviceId + "_" + timestamp.toString();
    }
}
