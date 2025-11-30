package pe.iotteam.plantcare.community.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.iotteam.plantcare.community.domain.model.entities.Post;
import pe.iotteam.plantcare.community.domain.model.entities.PostReaction;

import java.util.UUID;

@Repository
public interface PostReactionRepository extends JpaRepository<PostReaction, UUID> {
    long countByPostId(Long postId);

    boolean existsByPostAndUserId(Post post, UUID userId);

    void deleteByPostAndUserId(Post post, UUID userId);
}
