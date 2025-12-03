package pe.iotteam.plantcare.analytics.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * JPA Entity for sensor data records
 */
@Entity
@Table(name = "sensor_data_analytics", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"device_id", "timestamp"}))
@Getter
@Setter
@NoArgsConstructor
public class SensorDataEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "air_temperature_c")
    private Double airTemperatureC;

    @Column(name = "air_humidity_pct")
    private Double airHumidityPct;

    @Column(name = "light_intensity_lux")
    private Integer lightIntensityLux;

    @Column(name = "soil_moisture_pct")
    private Integer soilMoisturePct;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "ingested_at", nullable = false)
    private LocalDateTime ingestedAt;

    public SensorDataEntity(String deviceId, Double airTemperatureC, Double airHumidityPct, 
                           Integer lightIntensityLux, Integer soilMoisturePct, LocalDateTime timestamp) {
        this.deviceId = deviceId;
        this.airTemperatureC = airTemperatureC;
        this.airHumidityPct = airHumidityPct;
        this.lightIntensityLux = lightIntensityLux;
        this.soilMoisturePct = soilMoisturePct;
        this.timestamp = timestamp;
        this.ingestedAt = LocalDateTime.now();
    }
}
