package dao;

import entity.User;

import java.util.List;

public interface UserDao {
    void create(User user);

    void updateUserPassword(User user, String newPassword);

    void deleteFromUser(User user);

    User selectFromUserByFirstNameAndLastName(User user);

    List<User> findAll();

    User findByEmail(User userFind);

    User findById(int id);

    void updateUser(User user);

}
