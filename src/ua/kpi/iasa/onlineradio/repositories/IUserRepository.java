package ua.kpi.iasa.onlineradio.repositories;

import ua.kpi.iasa.onlineradio.models.User;
import java.util.List;
import java.util.Optional;

public interface IUserRepository {

    Optional<User> findById(int id);

    Optional<User> findByUsername(String username);

    List<User> findAll();

    void save(User user);

}