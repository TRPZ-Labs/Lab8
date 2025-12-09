package ua.kpi.iasa.onlineradio.models;

public class BroadcastSettings {
    private int bitrate;
    private String format;
    private String streamUrl;

    public BroadcastSettings(int bitrate, String format, String streamUrl) {
        this.bitrate = bitrate;
        this.format = format;
        this.streamUrl = streamUrl;
    }

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    @Override
    public String toString() {
        return "BroadcastSettings{" +
               "bitrate=" + bitrate + "kbps" +
               ", format='" + format + '\'' +
               ", streamUrl='" + streamUrl + '\'' +
               '}';
    }
}
