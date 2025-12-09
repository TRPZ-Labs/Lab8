package ua.kpi.iasa.onlineradio.data;

// import ua.kpi.iasa.onlineradio.models.Administrator;
import ua.kpi.iasa.onlineradio.models.User;
import ua.kpi.iasa.onlineradio.repositories.IUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepository implements IUserRepository {

    private static final Map<Integer, User> userStore = new ConcurrentHashMap<>();
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(userStore.get(id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userStore.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userStore.values());
    }

    @Override
    public void save(User user) {
        int newId = idCounter.incrementAndGet();
        user.setId(newId);
        userStore.put(newId, user);
    }
}
