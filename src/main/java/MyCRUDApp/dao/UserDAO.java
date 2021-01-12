package MyCRUDApp.dao;

import MyCRUDApp.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();
    public User getUser(Long id);
    public void deleteUser(User user);
    public User saveUser(User user);
    public User updateUser(User user);

}
