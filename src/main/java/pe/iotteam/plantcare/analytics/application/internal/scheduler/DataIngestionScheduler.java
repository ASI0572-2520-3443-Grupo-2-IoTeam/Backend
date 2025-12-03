package pe.iotteam.plantcare.analytics.application.internal.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pe.iotteam.plantcare.analytics.domain.model.commands.IngestSensorDataCommand;
import pe.iotteam.plantcare.analytics.application.internal.commandservices.SensorDataIngestionCommandService;

/**
 * Scheduled task for automatic incremental data ingestion
 * Runs every 5 minutes to fetch new sensor data from external API
 */
@Component
public class DataIngestionScheduler {

    private static final Logger log = LoggerFactory.getLogger(DataIngestionScheduler.class);
    private final SensorDataIngestionCommandService ingestionService;

    public DataIngestionScheduler(SensorDataIngestionCommandService ingestionService) {
        this.ingestionService = ingestionService;
    }

    // @Scheduled(cron = "0 */5 * * * *") // Deshabilitado - usar endpoint manual
    public void scheduledDataIngestion() {
        log.info("Starting scheduled data ingestion");
        try {
            int count = ingestionService.handle(new IngestSensorDataCommand());
            log.info("Ingestion completed: {} new records", count);
        } catch (Exception e) {
            log.error("Ingestion failed: {}", e.getMessage(), e);
        }
    }
    
    /**
     * Run ingestion once on application startup
     */
    @Scheduled(initialDelay = 15000, fixedDelay = Long.MAX_VALUE)
    public void initialDataIngestion() {
        log.info("Running initial data ingestion on startup");
        try {
            int count = ingestionService.handle(new IngestSensorDataCommand());
            log.info("Initial ingestion completed: {} records ingested", count);
        } catch (Exception e) {
            log.error("Initial data ingestion failed: {}", e.getMessage(), e);
        }
    }
}
