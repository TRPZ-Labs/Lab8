package ua.kpi.iasa.onlineradio.models;

import java.time.LocalDateTime;

public class PlaybackEvent {
    private long id;
    private int userId;
    private int trackId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public PlaybackEvent(int userId, int trackId) {
        this.userId = userId;
        this.trackId = trackId;
        this.startTime = LocalDateTime.now();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public int getUserId() { return userId; }
    public int getTrackId() { return trackId; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    

    public void endEvent() {
        this.endTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "PlaybackEvent{" +
               "userId=" + userId +
               ", trackId=" + trackId +
               ", startTime=" + startTime +
               '}';
    }
}
