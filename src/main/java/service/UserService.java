package service;

import dao.impl.UserDaoImpl;
import entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    UserDaoImpl userDaoImpl = new UserDaoImpl();

    public void updateUser(User user) {
        userDaoImpl.updateUser(user);
    }

    public void create(User user) {
        userDaoImpl.create(user);
    }

    public User findByEmail(User user) {
        return userDaoImpl.findByEmail(user);
    }

    public User findById(int id) {
        return userDaoImpl.findById(id);
    }

    public void updateUserPasswordByName(User user, String newPassword) {
        userDaoImpl.updateUserPassword(user, newPassword);
    }

    public void deleteFromUser(User user) {
        userDaoImpl.deleteFromUser(user);
    }

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        users = userDaoImpl.findAll();
        return users;
    }

    public User findByFirstAndLastName(User user) {
        return userDaoImpl.selectFromUserByFirstNameAndLastName(user);
    }

}
