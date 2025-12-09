package ua.kpi.iasa.onlineradio.models;

// import ua.kpi.iasa.onlineradio.models.Track;
import ua.kpi.iasa.onlineradio.repositories.ITrackRepository;
import java.util.List;
import java.util.stream.Collectors;

public class MusicLibrary {
    // Залежність від інтерфейсу, а не від конкретної реалізації
    private final ITrackRepository trackRepository;

    /**
     * Конструктор, що приймає репозиторій треків.
     * Це приклад Dependency Injection.
     * @param trackRepository Репозиторій для роботи з треками.
     */
    public MusicLibrary(ITrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    /**
     * Додає новий трек до бібліотеки.
     * @param track Новий трек.
     */
    public void addTrack(Track track) {
        // Делегує збереження репозиторію
        trackRepository.save(track);
        System.out.println("Трек '" + track.getTitle() + "' додано до бібліотеки.");
    }

    /**
     * Повертає всі треки з бібліотеки.
     * @return Список всіх треків.
     */
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    /**
     * Знаходить треки за назвою або виконавцем.
     * @param query Пошуковий запит.
     * @return Список знайдених треків.
     */
    public List<Track> findTrack(String query) {
        String lowerCaseQuery = query.toLowerCase();
        return trackRepository.findAll().stream()
                .filter(track -> track.getTitle().toLowerCase().contains(lowerCaseQuery) ||
                                 track.getArtist().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }
}