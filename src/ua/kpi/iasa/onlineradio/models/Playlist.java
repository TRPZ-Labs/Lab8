package ua.kpi.iasa.onlineradio.models;

import ua.kpi.iasa.onlineradio.models.iterators.ITrackIterator;
import ua.kpi.iasa.onlineradio.models.iterators.InfinitePlaylistIterator;
import ua.kpi.iasa.onlineradio.models.iterators.ShuffleIterator;
import ua.kpi.iasa.onlineradio.models.visitor.IVisitable;
import ua.kpi.iasa.onlineradio.models.visitor.IVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist implements IVisitable {
    private int id;
    private String name;
    private List<Track> tracks;
    private IterationMode mode;

    public Playlist(int id, String name) {
        this.id = id;
        this.name = name;
        this.tracks = new ArrayList<>();
        this.mode = IterationMode.INFINITE;
    }

    // --- Реалізація Visitor ---
    @Override
    public void accept(IVisitor visitor) {
        // Спочатку відвідуємо сам плейлист
        visitor.visit(this);

        // Потім, логіка відвідувача може захотіти пройтися по треках.
        // Але часто саме відвідувач вирішує, чи йти вглиб.
        // Для спрощення тут ми дозволимо відвідувачу самому ітерувати список tracks,
        // оскільки ми надали геттер getTracks().
        // АБО можна викликати accept для кожного трека тут:
        for(Track track : tracks) {
            track.accept(visitor);
        }
    }
    // --------------------------

    public ITrackIterator createIterator() {
        switch (this.mode) {
            case SHUFFLE: return new ShuffleIterator(this.tracks);
            case ONE_TIME: return new InfinitePlaylistIterator(this.tracks); // Заглушка
            case INFINITE:
            default: return new InfinitePlaylistIterator(this.tracks);
        }
    }

    public void setMode(IterationMode mode) { this.mode = mode; }
    public IterationMode getMode() { return mode; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Track> getTracks() {
        return Collections.unmodifiableList(tracks);
    }

    public void addTrack(Track track) {
        if (track != null && !tracks.contains(track)) {
            tracks.add(track);
        }
    }

    @Override
    public String toString() {
        return "Playlist{id=" + id + ", name='" + name + "', mode=" + mode + "}";
    }
}