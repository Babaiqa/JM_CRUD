package MyCRUDApp.dao;

import MyCRUDApp.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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
    public User getUser(Long id) {
        return (User) entityManager.createQuery("select u from User u where u.id= :id")
                .setParameter("id", id)
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
