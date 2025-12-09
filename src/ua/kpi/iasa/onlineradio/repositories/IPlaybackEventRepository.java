package ua.kpi.iasa.onlineradio.repositories;

import ua.kpi.iasa.onlineradio.models.PlaybackEvent;
import java.util.List;

public interface IPlaybackEventRepository {

    void save(PlaybackEvent event);

    List<PlaybackEvent> findAll();
}
