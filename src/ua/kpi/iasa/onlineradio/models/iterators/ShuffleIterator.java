package ua.kpi.iasa.onlineradio.models.iterators;

import ua.kpi.iasa.onlineradio.models.Track;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffleIterator implements ITrackIterator {
    private final List<Track> tracks;
    private int currentPosition = 0;

    public ShuffleIterator(List<Track> originalTracks) {
        this.tracks = new ArrayList<>(originalTracks);
        Collections.shuffle(this.tracks);
    }

    @Override
    public boolean hasNext() {
        return currentPosition < tracks.size();
    }

    @Override
    public Track next() {
        if (!hasNext()) {
            return null;
        }
        Track track = tracks.get(currentPosition);
        currentPosition++;
        return track;
    }
}