package ua.kpi.iasa.onlineradio.models;

import ua.kpi.iasa.onlineradio.models.iterators.ITrackIterator;
import ua.kpi.iasa.onlineradio.models.adapter.AudioPlayer;
import ua.kpi.iasa.onlineradio.models.adapter.IMediaPlayer;

public class Streamer {
    private Playlist activePlaylist;
    private Track currentTrack;
    private ITrackIterator trackIterator;

    private IMediaPlayer mediaPlayer;

    public Streamer() {
        this.mediaPlayer = new AudioPlayer();
    }

    public void setActivePlaylist(Playlist playlist) {
        this.activePlaylist = playlist;
        if (playlist != null) {
            this.trackIterator = playlist.createIterator();
        } else {
            this.trackIterator = null;
        }
        this.currentTrack = null;
        System.out.println("Активний плейлист змінено на: " + (playlist != null ? playlist.getName() : "null"));
    }

    public void play() {
        if (trackIterator == null || !trackIterator.hasNext()) {
            System.out.println("Помилка: плейлист не встановлено або він порожній.");
            return;
        }

        if (currentTrack == null) {
            nextTrack();
        } else {
            playCurrentTrack();
        }
    }

    public void nextTrack() {
        if (trackIterator == null) {
            System.out.println("Неможливо перемкнути трек: плейлист не активний.");
            return;
        }

        currentTrack = trackIterator.next();

        if (currentTrack != null) {
            playCurrentTrack();
        } else {
            System.out.println("Плейлист порожній.");
        }
    }

    // Метод для запуску програвання через Adapter
    private void playCurrentTrack() {
        String filePath = currentTrack.getFilePath();
        // Проста логіка визначення формату за розширенням файлу
        String audioType = "mp3";

        if (filePath != null && filePath.contains(".")) {
            audioType = filePath.substring(filePath.lastIndexOf(".") + 1);
        }

        System.out.println("Спроба відтворення: " + currentTrack.getArtist() + " - " + currentTrack.getTitle());
        // Викликаємо метод play нашого AudioPlayer
        mediaPlayer.play(audioType, filePath);
    }

    public Track getCurrentTrack() {
        return currentTrack;
    }
}