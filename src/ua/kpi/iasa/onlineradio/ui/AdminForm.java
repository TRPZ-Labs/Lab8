package ua.kpi.iasa.onlineradio.ui;

import ua.kpi.iasa.onlineradio.models.MusicLibrary;
import ua.kpi.iasa.onlineradio.models.Track;

import javax.swing.*;
import java.awt.*;

public class AdminForm extends JFrame {
    private final MusicLibrary library;
    private final DefaultListModel<String> listModel;

    public AdminForm(MusicLibrary library) {
        this.library = library;

        setTitle("Панель Адміністратора - Керування бібліотекою");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        listModel = new DefaultListModel<>();
        refreshTrackList();
        JList<String> trackList = new JList<>(listModel);
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Бібліотека треків"));
        listPanel.add(new JScrollPane(trackList), BorderLayout.CENTER);

        add(listPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Новий трек"));

        JTextField titleField = new JTextField();
        JTextField artistField = new JTextField();
        JTextField pathField = new JTextField("/music/new.mp3");

        inputPanel.add(new JLabel("Назва:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Виконавець:"));
        inputPanel.add(artistField);
        inputPanel.add(new JLabel("Шлях до файлу:"));
        inputPanel.add(pathField);

        JButton addButton = new JButton("Додати в БД");
        inputPanel.add(new JLabel("")); // пусте місце
        inputPanel.add(addButton);

        rightPanel.add(inputPanel, BorderLayout.NORTH);

        JButton logoutButton = new JButton("Вийти з системи");
        rightPanel.add(logoutButton, BorderLayout.SOUTH);

        add(rightPanel, BorderLayout.EAST);

        addButton.addActionListener(e -> {
            String title = titleField.getText();
            String artist = artistField.getText();
            String path = pathField.getText();

            if (title.isEmpty() || artist.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Заповніть назву та виконавця!", "Помилка", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Track newTrack = new Track(0, title, artist, path);
            library.addTrack(newTrack);

            refreshTrackList();

            // Очистка полів
            titleField.setText("");
            artistField.setText("");
            JOptionPane.showMessageDialog(this, "Трек успішно додано!");
        });

        logoutButton.addActionListener(e -> {
            this.dispose();
            System.exit(0);
        });
    }

    private void refreshTrackList() {
        listModel.clear();
        for (Track t : library.getAllTracks()) {
            listModel.addElement(String.format("[%d] %s - %s (Likes: %d)",
                    t.getId(), t.getArtist(), t.getTitle(), t.getLikeCount()));
        }
    }
}