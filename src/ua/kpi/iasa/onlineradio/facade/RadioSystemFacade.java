package ua.kpi.iasa.onlineradio.facade;

import ua.kpi.iasa.onlineradio.data.*;
import ua.kpi.iasa.onlineradio.models.*;
import ua.kpi.iasa.onlineradio.models.visitor.ReportVisitor;
import ua.kpi.iasa.onlineradio.models.visitor.XmlExportVisitor;
import ua.kpi.iasa.onlineradio.repositories.*;

import java.util.Optional;

public class RadioSystemFacade {
    // ... існуючі поля (userRepo, trackRepo, playlistRepo, historyRepo, library, streamer, currentUser)
    private final IUserRepository userRepo;
    private final ITrackRepository trackRepo;
    private final IPlaylistRepository playlistRepo;
    private final IPlaybackEventRepository historyRepo;
    private final MusicLibrary library;
    private final Streamer streamer;
    private User currentUser;

    public RadioSystemFacade() {
        this.trackRepo = new TrackRepository();
        this.userRepo = new UserRepository();
        this.playlistRepo = new PlaylistRepository();
        this.historyRepo = new PlaybackEventRepository();
        this.library = new MusicLibrary(trackRepo);
        this.streamer = new Streamer();
        setupInitialData();
    }

    // ... (Методи login, play, nextTrack, like, setPlaylist, changePlaybackMode, getCurrentTrack залишаються без змін)
    public boolean login(String username, String password) {
        Optional<User> userOpt = userRepo.findByUsername(username);
        if (userOpt.isPresent() && userOpt.get().getPasswordHash().equals(password)) {
            this.currentUser = userOpt.get();
            return true;
        }
        return false;
    }

    public void setPlaylist(int playlistId) {
        playlistRepo.findById(playlistId).ifPresent(streamer::setActivePlaylist);
    }

    public void play() {
        streamer.play();
        logHistory();
    }

    public void nextTrack() {
        streamer.nextTrack();
        logHistory();
    }

    public Track getCurrentTrack() {
        return streamer.getCurrentTrack();
    }

    public void likeCurrentTrack() {
        Track current = streamer.getCurrentTrack();
        if (current != null && currentUser != null) {
            current.addLike(currentUser);
        }
    }

    public void changePlaybackMode(IterationMode mode) {
        playlistRepo.findById(1).ifPresent(p -> {
            p.setMode(mode);
            streamer.setActivePlaylist(p);
        });
    }

    public User getCurrentUser() { return currentUser; }

    // --- НОВІ МЕТОДИ ДЛЯ VISITOR ---

    public String exportPlaylistToXml(int playlistId) {
        Optional<Playlist> playlistOpt = playlistRepo.findById(playlistId);
        if (playlistOpt.isPresent()) {
            XmlExportVisitor visitor = new XmlExportVisitor();
            Playlist playlist = playlistOpt.get();

            // Запускаємо відвідувача
            playlist.accept(visitor);

            // Додаємо закриваючий тег (специфіка нашої простої реалізації)
            visitor.closePlaylist();

            return visitor.getXml();
        }
        return "<error>Playlist not found</error>";
    }

    public String generatePlaylistReport(int playlistId) {
        Optional<Playlist> playlistOpt = playlistRepo.findById(playlistId);
        if (playlistOpt.isPresent()) {
            ReportVisitor visitor = new ReportVisitor();
            playlistOpt.get().accept(visitor);
            return visitor.getReport();
        }
        return "Плейлист не знайдено.";
    }

    private void logHistory() {
        Track current = streamer.getCurrentTrack();
        if (current != null && currentUser != null) {
            historyRepo.save(new PlaybackEvent(currentUser.getId(), current.getId()));
        }
    }

    private void setupInitialData() {
        userRepo.save(new Administrator(0, "admin", "admin"));
        userRepo.save(new User(0, "listener", "1234"));

        Track t1 = new Track(0, "Bohemian Rhapsody", "Queen", "music/queen.mp3");
        Track t2 = new Track(0, "Smells Like Teen Spirit", "Nirvana", "music/nirvana.mp4");
        Track t3 = new Track(0, "Shape of You", "Ed Sheeran", "music/ed.mp3");
        Track t4 = new Track(0, "Believer", "Imagine Dragons", "music/believer.mp3");

        trackRepo.save(t1);
        trackRepo.save(t2);
        trackRepo.save(t3);
        trackRepo.save(t4);

        Playlist p1 = new Playlist(0, "Best Rock");
        p1.addTrack(t1);
        p1.addTrack(t2);
        p1.addTrack(t3);
        p1.addTrack(t4);
        playlistRepo.save(p1);
    }
}