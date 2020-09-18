package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    void edit(User user);

    User getById(int id);

    boolean saveUser(User user);

    void removeUserById(User user);

    List<User> getAllUsers();

}
