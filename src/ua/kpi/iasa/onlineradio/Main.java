package ua.kpi.iasa.onlineradio;

import ua.kpi.iasa.onlineradio.facade.RadioSystemFacade;
import ua.kpi.iasa.onlineradio.ui.LoginForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        System.out.println("--- Запуск Online Radio System (with Facade) ---");

        // Створюємо фасад. Він сам ініціалізує БД, бібліотеку і стрімер.
        RadioSystemFacade radioFacade = new RadioSystemFacade();

        // Встановлюємо активний плейлист за замовчуванням через фасад
        radioFacade.setPlaylist(1);

        SwingUtilities.invokeLater(() -> {
            // Передаємо лише фасад
            new LoginForm(radioFacade).setVisible(true);
        });
    }
}