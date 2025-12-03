package pe.iotteam.plantcare.user.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.iotteam.plantcare.user.domain.model.entities.AchievementEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface AchievementRepository extends JpaRepository<AchievementEntity, String> {
    List<AchievementEntity> findByUserId(String userId);

    /**
     * Buscar logros por UUID de usuario
     * Internamente convierte UUID a String para compatibilidad con la BD
     */
    default List<AchievementEntity> findByUserIdUUID(UUID userId) {
        return findByUserId(userId.toString());
    }
}
