package ua.kpi.iasa.onlineradio.repositories;

import ua.kpi.iasa.onlineradio.models.Track;
import java.util.List;
import java.util.Optional;


public interface ITrackRepository {

    Optional<Track> findById(int id);

    List<Track> findAll();

    void save(Track track);

    void update(Track track);

    void deleteById(int id);

}
