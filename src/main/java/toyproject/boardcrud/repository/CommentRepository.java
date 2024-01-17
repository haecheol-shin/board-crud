package toyproject.boardcrud.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.boardcrud.domain.Comment;
import toyproject.boardcrud.domain.Post;
import toyproject.boardcrud.domain.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public Comment findOne(Long id) {

        return em.find(Comment.class, id);
    }

    public List<Comment> findComments(Post post) {
        return em.createQuery("SELECT c FROM Comment c WHERE c.post = :post ", Comment.class)
                .setParameter("post", post)
                .getResultList();
    }

    public List<Comment> findByCommentAuthor(User author) {
        return em.createQuery("SELECT c FROM Comment c WHERE c.commentAuthor = :author ", Comment.class)
                .setParameter("author", author)
                .getResultList();
    }

    public void save(Comment comment) {

        em.persist(comment);
    }

    public void deleteComment(Long id, User author) {
        em.createQuery("DELETE FROM Comment c WHERE c.id = :commentId AND c.commentAuthor = :author")
                .setParameter("commentId", id)
                .setParameter("author", author)
                .executeUpdate();

    }



}
