package web.service;

import web.model.User;

import java.util.List;

public interface UserService {

    void edit(User user);

    User getById(int id);

    boolean saveUser(User user);

    void removeUserById(User user);

    List<User> getAllUsers();

}
