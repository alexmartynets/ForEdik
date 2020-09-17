package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    void edit(User user);

    void saveUser(User user);

    User getById(int id);

    User getByName(String name);

    void removeUserById(User user);

    List<User> getAllUsers();

}
