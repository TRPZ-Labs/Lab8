package ua.kpi.iasa.onlineradio.repositories;

import ua.kpi.iasa.onlineradio.models.Playlist;
import java.util.List;
import java.util.Optional;


public interface IPlaylistRepository {

    Optional<Playlist> findById(int id);

    List<Playlist> findAll();

    void save(Playlist playlist);

    void update(Playlist playlist);

    void deleteById(int id);
}
