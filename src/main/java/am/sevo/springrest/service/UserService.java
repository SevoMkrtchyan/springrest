package am.sevo.springrest.service;

import am.sevo.springrest.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    boolean saveUser(User user);

    User getUserById(int id);

    boolean deleteUserById(int id);

}
