package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.Collections;
import java.util.List;

@Service
@Transactional

public class UserServiceInp implements UserService, UserDetailsService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void edit(User user) {
        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        userDao.edit(user);
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    @Transactional
    public boolean saveUser(User user) {
        User userFromDB = userDao.getByName(user.getUsername());

        if (userFromDB != null) {
            return false;
        }
        else {
            user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
            //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userDao.saveUser(user);
            return true;
        }

    }

    @Override
    @Transactional
    public void removeUserById(User user) {
        userDao.removeUserById(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userDao.getByName(name);
    }
}