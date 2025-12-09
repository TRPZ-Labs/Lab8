package ua.kpi.iasa.onlineradio.models.adapter;

public class Mp4Player implements IAdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // Нічого не робимо
    }

    @Override
    public void playMp4(String fileName) {
        System.out.println("Advanced Player: Програвання mp4 файлу. Ім'я: " + fileName);
    }
}