package ua.kpi.iasa.onlineradio.models.visitor;

import ua.kpi.iasa.onlineradio.models.Playlist;
import ua.kpi.iasa.onlineradio.models.Track;

public class XmlExportVisitor implements IVisitor {
    private StringBuilder xmlBuilder = new StringBuilder();

    public String getXml() {
        return xmlBuilder.toString();
    }

    @Override
    public void visit(Playlist playlist) {
        xmlBuilder.append("<playlist id=\"").append(playlist.getId()).append("\">\n")
                .append("  <name>").append(playlist.getName()).append("</name>\n")
                .append("  <tracks>\n");
        // Закриваючий тег буде додано логічно після треків,
        // але оскільки метод accept в Playlist викликає accept для треків одразу після visit(this),
        // нам треба трохи схитрувати або просто знати, що треки йдуть слідом.
        // У простій реалізації Visitor для ієрархій часто роблять visitEnter і visitLeave,
        // але для спрощення ми просто сформуємо заголовок тут.
    }

    // Цей метод викликається для кожного трека
    @Override
    public void visit(Track track) {
        xmlBuilder.append("    <track id=\"").append(track.getId()).append("\">\n")
                .append("      <title>").append(track.getTitle()).append("</title>\n")
                .append("      <artist>").append(track.getArtist()).append("</artist>\n")
                .append("      <duration>").append(track.getDuration().getSeconds()).append("s</duration>\n")
                .append("    </track>\n");
    }

    // Допоміжний метод для закриття тегів, який ми викличемо вручну в фасаді
    public void closePlaylist() {
        xmlBuilder.append("  </tracks>\n")
                .append("</playlist>");
    }
}