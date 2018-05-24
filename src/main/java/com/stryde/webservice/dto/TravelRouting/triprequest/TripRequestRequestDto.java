package com.stryde.webservice.dto.TravelRouting.triprequest;

import com.stryde.webservice.dto.TravelRouting.StopDto;

import java.time.LocalDateTime;

public class TripRequestRequestDto {

    private StopDto origin;
    private StopDto destination;
    private LocalDateTime datetime;
    //when false is arrival time
    private boolean isDepartureTime;

    public TripRequestRequestDto()  {
        origin = new StopDto();
        destination = new StopDto();
    }

    public StopDto getOrigin() {
        return origin;
    }

    public void setOrigin(StopDto origin) {
        this.origin = origin;
    }

    public StopDto getDestination() {
        return destination;
    }

    public void setDestination(StopDto destination) {
        this.destination = destination;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public boolean getIsDepartureTime() {
        return isDepartureTime;
    }

    public void setIsDepartureTime(boolean departureTime) {
        isDepartureTime = departureTime;
    }

    @Override
    public String toString() {
        return "TripRequestRequestDto{" +
                "origin=" + origin +
                ", destination=" + destination +
                ", datetime=" + datetime +
                ", isDepartureTime=" + isDepartureTime +
                '}';
    }
}
