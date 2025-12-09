package ua.kpi.iasa.onlineradio.models.iterators;

import ua.kpi.iasa.onlineradio.models.Track;
import java.util.List;

public class InfinitePlaylistIterator implements ITrackIterator {
    private final List<Track> tracks;
    private int currentPosition = 0;

    public InfinitePlaylistIterator(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public boolean hasNext() {
        return !tracks.isEmpty();
    }

    @Override
    public Track next() {
        if (tracks.isEmpty()) {
            return null;
        }
        Track track = tracks.get(currentPosition);
        currentPosition++;
        if (currentPosition >= tracks.size()) {
            currentPosition = 0;
        }
        return track;
    }
}