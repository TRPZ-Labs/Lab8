package ua.kpi.iasa.onlineradio.models.adapter;

public class VlcPlayer implements IAdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Advanced Player: Програвання vlc файлу. Ім'я: " + fileName);
    }

    @Override
    public void playMp4(String fileName) {
        // Нічого не робимо
    }
}