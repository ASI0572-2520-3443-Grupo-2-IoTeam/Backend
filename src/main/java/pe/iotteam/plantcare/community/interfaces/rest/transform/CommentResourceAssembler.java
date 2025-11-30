package pe.iotteam.plantcare.community.interfaces.rest.transform;

import pe.iotteam.plantcare.community.domain.model.entities.Comment;
import pe.iotteam.plantcare.community.interfaces.rest.resources.CommentResource;

public class CommentResourceAssembler {

    public static CommentResource toResource(Comment comment, String username) {
        return new CommentResource(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getAuthor().getId(),
                username,
                comment.getPost().getId());
    }
}