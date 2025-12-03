package pe.iotteam.plantcare.user.interfaces.rest.transform;

import pe.iotteam.plantcare.user.domain.model.entities.AchievementEntity;
import pe.iotteam.plantcare.user.domain.model.entities.UserProfileEntity;
import pe.iotteam.plantcare.user.interfaces.rest.resources.AchievementResource;
import pe.iotteam.plantcare.user.interfaces.rest.resources.UserProfileResource;
import pe.iotteam.plantcare.user.interfaces.rest.resources.UserStatsResource;
import org.springframework.security.core.Authentication;

import java.util.UUID;

/**
 * Assembler para transformar UserProfileEntity y AchievementEntity a DTOs de respuesta.
 * Centraliza la lógica de transformación entre capas de dominio e interfaces.
 */
public class UserProfileResourceAssembler {

    /**
     * Transforma una UserProfileEntity a UserProfileResource
     */
    public static UserProfileResource toResource(
            UserProfileEntity entity,
            String email,
            String username,
            UUID userId,
            UserStatsResource stats) {
        return new UserProfileResource(
                userId,
                email,
                username,
                entity.getFullName(),
                entity.getPhone(),
                entity.getBio(),
                entity.getLocation(),
                entity.getAvatarUrl(),
                entity.getJoinDate(),
                entity.getLastLogin(),
                stats
        );
    }

    /**
     * Transforma una AchievementEntity a AchievementResource
     */
    public static AchievementResource toAchievementResource(AchievementEntity entity) {
        return new AchievementResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getIcon(),
                entity.getEarnedDate(),
                entity.getStatus() != null ? entity.getStatus().name().toLowerCase() : "locked"
        );
    }
}
