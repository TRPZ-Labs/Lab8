package ua.kpi.iasa.onlineradio.models.adapter;

public class AudioPlayer implements IMediaPlayer {
    private MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        // Вбудована підтримка mp3 (імітація старого коду)
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Built-in Player: Програвання mp3 файлу. Ім'я: " + fileName);
        }
        // Використання адаптера для інших форматів
        else if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        else {
            System.out.println("Invalid media. " + audioType + " формат не підтримується.");
        }
    }
}