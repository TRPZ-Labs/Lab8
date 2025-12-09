package ua.kpi.iasa.onlineradio.data;

import ua.kpi.iasa.onlineradio.models.Playlist;
// import ua.kpi.iasa.onlineradio.models.Track;
import ua.kpi.iasa.onlineradio.repositories.IPlaylistRepository;
//import ua.kpi.iasa.onlineradio.repositories.ITrackRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PlaylistRepository implements IPlaylistRepository {

    private static final Map<Integer, Playlist> playlistStore = new ConcurrentHashMap<>();
    private static final AtomicInteger idCounter = new AtomicInteger(0);

    @Override
    public Optional<Playlist> findById(int id) {
        return Optional.ofNullable(playlistStore.get(id));
    }

    @Override
    public List<Playlist> findAll() {
        return new ArrayList<>(playlistStore.values());
    }

    @Override
    public void save(Playlist playlist) {
        int newId = idCounter.incrementAndGet();
        playlist.setId(newId);
        playlistStore.put(newId, playlist);
    }

    @Override
    public void update(Playlist playlist) {
        playlistStore.put(playlist.getId(), playlist);
    }

    @Override
    public void deleteById(int id) {
        playlistStore.remove(id);
    }
}
