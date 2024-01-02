package toyproject.boardcrud.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.boardcrud.domain.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {

        em.persist(user);
    }
    public User findOne(Long id) {

        return em.find(User.class, id);
    }

    public User findById(String id) {

        return em.createQuery("select u from User u where u.id = :userId", User.class)
                .setParameter("userId", id)
                .getSingleResult();
    }

    public long countUsers() {
        return em.createQuery("SELECT COUNT(u) FROM User u", Long.class)
                .getSingleResult();
    }

}
