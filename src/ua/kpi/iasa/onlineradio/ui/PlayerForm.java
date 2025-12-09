package ua.kpi.iasa.onlineradio.ui;

import ua.kpi.iasa.onlineradio.facade.RadioSystemFacade;
import ua.kpi.iasa.onlineradio.models.IterationMode;
import ua.kpi.iasa.onlineradio.models.Track;

import javax.swing.*;
import java.awt.*;

public class PlayerForm extends JFrame {
    private final RadioSystemFacade facade;

    private JLabel artistLabel;
    private JLabel titleLabel;
    private JLabel statusLabel;

    public PlayerForm(RadioSystemFacade facade) {
        this.facade = facade;

        setTitle("Online Radio Player - " + facade.getCurrentUser().getUsername());
        setSize(500, 400); // Збільшив розмір для нових кнопок
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        infoPanel.setBackground(Color.DARK_GRAY);

        statusLabel = new JLabel("Радіо готове", SwingConstants.CENTER);
        statusLabel.setForeground(Color.LIGHT_GRAY);

        titleLabel = new JLabel("---", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        artistLabel = new JLabel("---", SwingConstants.CENTER);
        artistLabel.setForeground(Color.CYAN);

        infoPanel.add(statusLabel);
        infoPanel.add(titleLabel);
        infoPanel.add(artistLabel);

        add(infoPanel, BorderLayout.CENTER);

        // Панель керування
        JPanel controlsPanel = new JPanel(new GridLayout(3, 1)); // Три ряди

        JPanel playbackPanel = new JPanel();
        JButton playButton = new JButton("▶ Play");
        JButton nextButton = new JButton("⏭ Next");
        JButton likeButton = new JButton("❤️ Like");
        playbackPanel.add(playButton);
        playbackPanel.add(nextButton);
        playbackPanel.add(likeButton);

        JPanel modePanel = new JPanel();
        JButton shuffleButton = new JButton("🔀 Shuffle");
        JButton infiniteButton = new JButton("🔁 Infinite");
        modePanel.add(new JLabel("Mode: "));
        modePanel.add(infiniteButton);
        modePanel.add(shuffleButton);

        // --- Нова панель для Visitor ---
        JPanel toolsPanel = new JPanel();
        JButton reportButton = new JButton("📄 Звіт (Report)");
        JButton xmlButton = new JButton("💾 Експорт XML");
        toolsPanel.add(reportButton);
        toolsPanel.add(xmlButton);

        controlsPanel.add(playbackPanel);
        controlsPanel.add(modePanel);
        controlsPanel.add(toolsPanel);

        add(controlsPanel, BorderLayout.SOUTH);

        // --- Обробники подій ---

        playButton.addActionListener(e -> {
            facade.play();
            statusLabel.setText("Зараз грає:");
            updateTrackInfo();
        });

        nextButton.addActionListener(e -> {
            facade.nextTrack();
            updateTrackInfo();
        });

        likeButton.addActionListener(e -> {
            facade.likeCurrentTrack();
            Track current = facade.getCurrentTrack();
            if (current != null) {
                JOptionPane.showMessageDialog(this, "Вподобано: " + current.getTitle());
            }
        });

        shuffleButton.addActionListener(e -> {
            facade.changePlaybackMode(IterationMode.SHUFFLE);
            statusLabel.setText("Режим: Shuffle");
        });

        infiniteButton.addActionListener(e -> {
            facade.changePlaybackMode(IterationMode.INFINITE);
            statusLabel.setText("Режим: Infinite");
        });

        // --- Visitor Actions ---
        reportButton.addActionListener(e -> {
            // Генеруємо звіт для плейлиста з ID=1
            String report = facade.generatePlaylistReport(1);
            JTextArea textArea = new JTextArea(10, 30);
            textArea.setText(report);
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Звіт по плейлисту", JOptionPane.INFORMATION_MESSAGE);
        });

        xmlButton.addActionListener(e -> {
            // Експортуємо в XML плейлист з ID=1
            String xml = facade.exportPlaylistToXml(1);
            JTextArea textArea = new JTextArea(10, 30);
            textArea.setText(xml);
            textArea.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "XML Експорт", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void updateTrackInfo() {
        Track track = facade.getCurrentTrack();
        if (track != null) {
            titleLabel.setText(track.getTitle());
            artistLabel.setText(track.getArtist());
        }
    }
}