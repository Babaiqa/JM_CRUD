package MyCRUDApp.dao;

import MyCRUDApp.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAllUsers();
    User getUser(Long id);
    void deleteUser(Long id);
    User saveUser(User user);
    User updateUser(User user);

}
