package com.stryde.webservice.dto.TravelRouting.triprequest;

import java.time.LocalDateTime;

public class TripDto {

    private Long tripId;
    private String line;
    private int numberOfChanges;
    private LocalDateTime boardTime;
    private LocalDateTime alightTime;
    private int duration;


    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getNumberOfChanges() {
        return numberOfChanges;
    }

    public void setNumberOfChanges(int numberOfChanges) {
        this.numberOfChanges = numberOfChanges;
    }

    public LocalDateTime getBoardTime() {
        return boardTime;
    }

    public void setBoardTime(LocalDateTime boardTime) {
        this.boardTime = boardTime;
    }

    public LocalDateTime getAlightTime() {
        return alightTime;
    }

    public void setAlightTime(LocalDateTime alightTime) {
        this.alightTime = alightTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "TripDto{" +
                ", tripId=" + tripId +
                ", line='" + line + '\'' +
                ", numberOfChanges=" + numberOfChanges +
                ", boardTime=" + boardTime +
                ", alightTime=" + alightTime +
                ", duration=" + duration +
                '}';
    }
}
