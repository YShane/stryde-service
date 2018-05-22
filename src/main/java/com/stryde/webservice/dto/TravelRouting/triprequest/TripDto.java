package com.stryde.webservice.dto.TravelRouting.triprequest;

import com.stryde.webservice.dto.TravelRouting.StopDto;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TripDto {

    private Long tripId;
    private String line;
    private LocalDateTime boardTime;
    private LocalDateTime alightTime;
    private int duration;

    private ArrayList<StopDto> stops = new ArrayList<>();

    //To be included later
    private TripMessage tripMessage = new TripMessage();


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

    public TripMessage getTripMessage() {
        return tripMessage;
    }

    public void setTripMessage(TripMessage tripMessage) {
        this.tripMessage = tripMessage;
    }

    public ArrayList<StopDto> getStops() {
        return stops;
    }

    public void setStops(ArrayList<StopDto> stops) {
        this.stops = stops;
    }
}
