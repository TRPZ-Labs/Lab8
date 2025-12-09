package ua.kpi.iasa.onlineradio.models.iterators;

import ua.kpi.iasa.onlineradio.models.Track;

public interface ITrackIterator {

    boolean hasNext();

    Track next();
}