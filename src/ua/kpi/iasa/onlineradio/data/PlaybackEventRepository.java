package ua.kpi.iasa.onlineradio.data;

import ua.kpi.iasa.onlineradio.models.PlaybackEvent;
import ua.kpi.iasa.onlineradio.repositories.IPlaybackEventRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class PlaybackEventRepository implements IPlaybackEventRepository {

    private static final List<PlaybackEvent> eventStore = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicLong idCounter = new AtomicLong(0);

    @Override
    public void save(PlaybackEvent event) {
        event.setId(idCounter.incrementAndGet());
        eventStore.add(event);
        System.out.println("Статистика: Зафіксовано нову подію прослуховування.");
    }

    @Override
    public List<PlaybackEvent> findAll() {
        // Повертаємо копію, щоб уникнути зміни списку ззовні
        return new ArrayList<>(eventStore);
    }
}
