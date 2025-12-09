package ua.kpi.iasa.onlineradio.models;
import ua.kpi.iasa.onlineradio.data.TrackRepository; // Assuming this is your implementation
import ua.kpi.iasa.onlineradio.repositories.ITrackRepository;
// import src.ua.kpi.iasa.onlineradio.services.MusicLibrary;
// import src.ua.kpi.iasa.onlineradio.services.Streamer;


public class RadioStation {
    private String name;
    private MusicLibrary musicLibrary;
    private Streamer streamer;
    private BroadcastSettings broadcastSettings;

    public RadioStation(String name) {
        this.name = name;

        ITrackRepository trackRepository = new TrackRepository();
        this.musicLibrary = new MusicLibrary(trackRepository);
        this.streamer = new Streamer();
        this.broadcastSettings = new BroadcastSettings(128, "MP3", "http://localhost:8000/stream");
        System.out.println("Радіостанція '" + this.name + "' створена.");
    }


    public MusicLibrary getMusicLibrary() {
        return musicLibrary;
    }

    public Streamer getStreamer() {
        return streamer;
    }

    public String getStatistics() {
        System.out.println("Збір статистики для станції '" + this.name + "'...");

        int totalTracks = musicLibrary.getAllTracks().size();
        return "Статистика: \n - Всього треків у бібліотеці: " + totalTracks + "\n - Популярний виконавець: Queen";
    }
    
    public String getName() {
        return name;
    }

    public BroadcastSettings getBroadcastSettings() {
        return broadcastSettings;
    }

    public void setBroadcastSettings(BroadcastSettings broadcastSettings) {
        this.broadcastSettings = broadcastSettings;
        System.out.println("Налаштування трансляції оновлено: " + broadcastSettings);
    }
}
