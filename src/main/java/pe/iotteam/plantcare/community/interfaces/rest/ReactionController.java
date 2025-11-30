package pe.iotteam.plantcare.community.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.iotteam.plantcare.community.application.internal.commandservices.CommunityContentCommandService;
import pe.iotteam.plantcare.community.infrastructure.persistence.jpa.repositories.PostReactionRepository;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/community/reactions")
@Tag(name = "Community Reactions", description = "Endpoints para gestionar las reacciones (likes) a las publicaciones.")
public class ReactionController {

    private final CommunityContentCommandService contentCommandService;
    private final PostReactionRepository reactionRepository;

    public ReactionController(CommunityContentCommandService contentCommandService,
            PostReactionRepository reactionRepository) {
        this.contentCommandService = contentCommandService;
        this.reactionRepository = reactionRepository;
    }

    @Operation(summary = "Reaccionar a una publicación (Toggle Like)", description = "Permite a un usuario dar o quitar 'like' a una publicación. "
            + "Si ya existe un like del usuario, se elimina. Si no existe, se crea.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reacción procesada correctamente. Devuelve true si se dio like, false si se quitó."),
            @ApiResponse(responseCode = "404", description = "Usuario o publicación no encontrada")
    })
    @PostMapping
    public ResponseEntity<Boolean> toggleLike(
            @RequestParam UUID userId,
            @RequestParam Long postId) {
        boolean isLiked = contentCommandService.toggleLike(userId, postId);
        return ResponseEntity.ok(isLiked);
    }

    @Operation(summary = "Obtener cantidad de likes de una publicación", description = "Devuelve el número total de reacciones (likes) que tiene una publicación específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cantidad de likes obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<Long> getLikesCount(@RequestParam Long postId) {
        long count = reactionRepository.countByPostId(postId);
        return ResponseEntity.ok(count);
    }
}
