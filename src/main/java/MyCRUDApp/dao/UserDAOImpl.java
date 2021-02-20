package MyCRUDApp.dao;

import MyCRUDApp.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Locale;

@Repository("userDAO")
public class UserDAOImpl implements UserDAO {


    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u").getResultList();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        return (User) entityManager.createQuery("select u from User u where u.email= :email")
                .setParameter("email", username.toLowerCase())
                .getSingleResult();
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User saveUser(User user) {

        entityManager.persist(user);

        return user;
    }

    @Override
    public User updateUser(User user) {
        entityManager.merge(user);
        return user;
    }
}
