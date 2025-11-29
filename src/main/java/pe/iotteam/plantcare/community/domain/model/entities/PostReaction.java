package pe.iotteam.plantcare.community.domain.model.entities;

import jakarta.persistence.*;
import pe.iotteam.plantcare.community.domain.model.aggregates.CommunityMember;

import java.util.UUID;

@Entity
@Table(name = "post_reactions", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "post_id", "user_id" })
})
public class PostReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private CommunityMember user;

    // Por ahora solo manejamos "likes", pero podríamos agregar un Enum ReactionType
    // si quisiéramos más tipos.
    // Para este requerimiento, la simple existencia de la fila cuenta como un like.

    protected PostReaction() {
    }

    public PostReaction(Post post, CommunityMember user) {
        this.post = post;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public CommunityMember getUser() {
        return user;
    }
}
