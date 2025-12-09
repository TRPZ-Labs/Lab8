package ua.kpi.iasa.onlineradio.models.visitor;

import ua.kpi.iasa.onlineradio.models.Playlist;
import ua.kpi.iasa.onlineradio.models.Track;

public interface IVisitor {
    void visit(Track track);
    void visit(Playlist playlist);
}