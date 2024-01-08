package toyproject.boardcrud.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.boardcrud.domain.Post;
import toyproject.boardcrud.domain.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    public void save(Post post) {
        if (post.getId() == null) {
            em.persist(post);
        } else {
            em.merge(post);
        }
    }

    public void deletePost(Long id, User author) {
        em.createQuery("DELETE FROM Post p WHERE p.id = :postId AND p.postAuthor = :author")
                .setParameter("postId", id)
                .setParameter("author", author)
                .executeUpdate();
    }

    public List<Post> findPostsByAuthor(User author) {
        return em.createQuery("SELECT p FROM Post p WHERE p.postAuthor = :author", Post.class)
                .setParameter("author", author)
                .getResultList();
    }

    public Post findPostByIdAndAuthor(Long id, User author) {
        return em.createQuery("SELECT p FROM Post p WHERE p.id = :postId AND p.postAuthor = :author", Post.class)
                .setParameter("postId", id)
                .setParameter("author", author)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }


}
