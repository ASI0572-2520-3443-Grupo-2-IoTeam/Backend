package pe.iotteam.plantcare.community.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.iotteam.plantcare.auth.domain.model.aggregates.UserAccount;
import pe.iotteam.plantcare.auth.infrastructure.persistence.jpa.repositories.UserRepository;
import pe.iotteam.plantcare.community.application.internal.commandservices.CommunityContentCommandService;
import pe.iotteam.plantcare.community.domain.model.entities.Comment;
import pe.iotteam.plantcare.community.infrastructure.persistence.jpa.repositories.CommentRepository;
import pe.iotteam.plantcare.community.interfaces.rest.resources.CommentResource;
import pe.iotteam.plantcare.community.interfaces.rest.transform.CommentResourceAssembler;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/community/comments")
@Tag(name = "Community Comments", description = "Endpoints para gestionar comentarios realizados por los usuarios dentro de la comunidad.")
public class CommentController {

        private final CommunityContentCommandService contentCommandService;
        private final UserRepository userRepository;
        private final CommentRepository commentRepository;

        public CommentController(
                        CommunityContentCommandService contentCommandService,
                        UserRepository userRepository,
                        CommentRepository commentRepository) {
                this.contentCommandService = contentCommandService;
                this.userRepository = userRepository;
                this.commentRepository = commentRepository;
        }

        @Operation(summary = "Agregar un comentario a un post", description = "Permite que un usuario registrado agregue un comentario a un post existente dentro de la comunidad. "
                        + "El comentario queda asociado tanto al usuario como al post indicado.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Comentario agregado exitosamente"),
                        @ApiResponse(responseCode = "400", description = "Solicitud inválida o parámetros faltantes"),
                        @ApiResponse(responseCode = "404", description = "El usuario o el post no existen"),
                        @ApiResponse(responseCode = "403", description = "El usuario no tiene permiso para comentar")
        })
        @PostMapping
        public ResponseEntity<CommentResource> addComment(
                        @RequestParam UUID userId,
                        @RequestParam Long postId,
                        @RequestParam String text) {
                Comment comment = contentCommandService.addComment(userId, postId, text);

                String username = userRepository.findById(userId)
                                .map(UserAccount::getUsername)
                                .orElse("Unknown");

                return ResponseEntity.ok(CommentResourceAssembler.toResource(comment, username));
        }

        @Operation(summary = "Listar todos los comentarios", description = "Devuelve una lista completa de todos los comentarios realizados en la comunidad.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Lista de comentarios obtenida exitosamente")
        })
        @GetMapping
        public ResponseEntity<List<CommentResource>> getAllComments() {
                var comments = commentRepository.findAll()
                                .stream()
                                .map(comment -> {
                                        String username = userRepository.findById(comment.getAuthor().getId())
                                                        .map(UserAccount::getUsername)
                                                        .orElse("Unknown");
                                        return CommentResourceAssembler.toResource(comment, username);
                                })
                                .toList();
                return ResponseEntity.ok(comments);
        }

        @Operation(summary = "Eliminar un comentario", description = "Permite a un usuario eliminar su propio comentario.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Comentario eliminado exitosamente"),
                        @ApiResponse(responseCode = "404", description = "Comentario no encontrado"),
                        @ApiResponse(responseCode = "403", description = "No autorizado para eliminar este comentario")
        })
        @DeleteMapping("/{commentId}")
        public ResponseEntity<Void> deleteComment(
                        @RequestParam UUID userId,
                        @PathVariable UUID commentId) {
                contentCommandService.deleteOwnComment(userId, commentId);
                return ResponseEntity.noContent().build();
        }
}