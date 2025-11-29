package pe.iotteam.plantcare.community.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.iotteam.plantcare.community.domain.model.aggregates.CommunityMember;
import pe.iotteam.plantcare.community.domain.model.entities.Comment;
import pe.iotteam.plantcare.community.domain.model.entities.Post;
import pe.iotteam.plantcare.community.domain.model.entities.PostReaction;
import pe.iotteam.plantcare.community.infrastructure.persistence.jpa.repositories.*;

import java.util.UUID;

@Service
public class CommunityContentCommandService {

    private final CommunityMemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final PostReactionRepository reactionRepository;

    public CommunityContentCommandService(
            CommunityMemberRepository memberRepository,
            PostRepository postRepository,
            CommentRepository commentRepository,
            PostReactionRepository reactionRepository) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.reactionRepository = reactionRepository;
    }

    @Transactional
    public Post createPost(UUID userId, String title, String content, String species, String tag) {
        CommunityMember author = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado"));

        Post post = new Post(title, content, species, tag, author);
        return postRepository.save(post);
    }

    @Transactional
    public Comment addComment(UUID userId, Long postId, String text) {
        CommunityMember author = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        Comment comment = new Comment(author, post, text);
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteOwnPost(UUID userId, Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post no encontrado"));

        if (!post.getAuthor().getId().equals(userId))
            throw new RuntimeException("No autorizado para eliminar este post");

        postRepository.delete(post);
    }

    @Transactional
    public boolean toggleLike(UUID userId, Long postId) {
        CommunityMember user = memberRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Miembro no encontrado"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post no encontrado"));

        if (reactionRepository.existsByPostAndUserId(post, userId)) {
            reactionRepository.deleteByPostAndUserId(post, userId);
            return false; // Like removed
        } else {
            reactionRepository.save(new PostReaction(post, user));
            return true; // Like added
        }
    }

    @Transactional
    public void deleteOwnComment(UUID userId, UUID commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

        if (!comment.getAuthor().getId().equals(userId)) {
            throw new RuntimeException("No autorizado para eliminar este comentario");
        }

        commentRepository.delete(comment);
    }
}