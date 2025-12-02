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
       uniqueConstraints = @UniqueConstraint(columnNames = {"device_id", "created_at"}))
@Getter
@Setter
@NoArgsConstructor
public class SensorDataEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "air_temperature_celsius")
    private Double airTemperatureCelsius;

    @Column(name = "air_humidity_percent")
    private Double airHumidityPercent;

    @Column(name = "luminosity_lux")
    private Integer luminosityLux;

    @Column(name = "soil_moisture_percent")
    private Integer soilMoisturePercent;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "ingested_at", nullable = false)
    private LocalDateTime ingestedAt;

    public SensorDataEntity(String deviceId, Double airTemperatureCelsius, Double airHumidityPercent, 
                           Integer luminosityLux, Integer soilMoisturePercent, LocalDateTime createdAt) {
        this.deviceId = deviceId;
        this.airTemperatureCelsius = airTemperatureCelsius;
        this.airHumidityPercent = airHumidityPercent;
        this.luminosityLux = luminosityLux;
        this.soilMoisturePercent = soilMoisturePercent;
        this.createdAt = createdAt;
        this.ingestedAt = LocalDateTime.now();
    }
}
