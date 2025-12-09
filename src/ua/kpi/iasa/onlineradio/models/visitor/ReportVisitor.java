package ua.kpi.iasa.onlineradio.models.visitor;

import ua.kpi.iasa.onlineradio.models.Playlist;
import ua.kpi.iasa.onlineradio.models.Track;

public class ReportVisitor implements IVisitor {
    private StringBuilder report = new StringBuilder();
    private int trackCount = 0;
    private long totalDurationSeconds = 0;

    public String getReport() {
        return report.toString() +
                "\n-----------------------------" +
                "\nВсього треків: " + trackCount +
                "\nЗагальна тривалість: " + totalDurationSeconds + " сек.";
    }

    @Override
    public void visit(Playlist playlist) {
        report.append("ЗВІТ ПО ПЛЕЙЛИСТУ: ").append(playlist.getName()).append("\n");
        report.append("Режим відтворення: ").append(playlist.getMode()).append("\n");
        report.append("-----------------------------\n");
    }

    @Override
    public void visit(Track track) {
        trackCount++;
        totalDurationSeconds += track.getDuration().getSeconds();
        report.append(String.format("%d. %s - %s (Лайків: %d)\n",
                trackCount, track.getArtist(), track.getTitle(), track.getLikeCount()));
    }
}