package ua.kpi.iasa.onlineradio.data;

import ua.kpi.iasa.onlineradio.models.Track;
import ua.kpi.iasa.onlineradio.repositories.ITrackRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TrackRepository implements ITrackRepository {

    private static final Map<Integer, Track> trackStore = new ConcurrentHashMap<>();
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    @Override
    public Optional<Track> findById(int id) {
        return Optional.ofNullable(trackStore.get(id));
    }

    @Override
    public List<Track> findAll() {
        return new ArrayList<>(trackStore.values());
    }

    @Override
    public void save(Track track) {
        int newId = idCounter.incrementAndGet();
        track.setId(newId);
        trackStore.put(newId, track);
    }
    
    @Override
    public void update(Track track) {
        trackStore.put(track.getId(), track);
    }
    
    @Override
    public void deleteById(int id) {
        trackStore.remove(id);
    }
}
