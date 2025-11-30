package pe.iotteam.plantcare.community.interfaces.rest.resources;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentResource(
                UUID commentId,
                String content,
                LocalDateTime createdAt,
                UUID authorId,
                String username,
                Long postId) {
}