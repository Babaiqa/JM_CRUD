package MyCRUDApp.dao;

import MyCRUDApp.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {


    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {

        return entityManager.createQuery("select user from User users").getResultList();
    }

    @Transactional
    @Override
    public User getUser(Long id) {

        return (User) entityManager.createQuery("select user from User users where user.id= :id")
                .getSingleResult();
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        entityManager.merge(user);
        return user;
    }
}
