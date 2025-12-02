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
    private Double airTemperatureCelsius;
    private Double airHumidityPercent;
    private Integer luminosityLux;
    private Integer soilMoisturePercent;
    private LocalDateTime createdAt;

    // Constructor for new records from API
    public SensorDataRecord(String deviceId, Double airTemperatureCelsius, Double airHumidityPercent, 
                           Integer luminosityLux, Integer soilMoisturePercent, LocalDateTime createdAt) {
        this.deviceId = deviceId;
        this.airTemperatureCelsius = airTemperatureCelsius;
        this.airHumidityPercent = airHumidityPercent;
        this.luminosityLux = luminosityLux;
        this.soilMoisturePercent = soilMoisturePercent;
        this.createdAt = createdAt;
    }

    // Constructor for persisted records
    public SensorDataRecord(SensorDataId id, String deviceId, Double airTemperatureCelsius, 
                           Double airHumidityPercent, Integer luminosityLux, Integer soilMoisturePercent, 
                           LocalDateTime createdAt) {
        this.id = id;
        this.deviceId = deviceId;
        this.airTemperatureCelsius = airTemperatureCelsius;
        this.airHumidityPercent = airHumidityPercent;
        this.luminosityLux = luminosityLux;
        this.soilMoisturePercent = soilMoisturePercent;
        this.createdAt = createdAt;
    }

    /**
     * Creates a unique identifier for this record based on device_id and timestamp
     * This is used to detect duplicates during ingestion
     */
    public String getUniqueIdentifier() {
        return deviceId + "_" + createdAt.toString();
    }
}
