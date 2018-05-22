package com.stryde.webservice.dto.TravelRouting.triprequest;

import com.stryde.webservice.dto.TravelRouting.StopDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TripRequestResponseDto {

    private List<TripDto> trips;
    private StopDto from = new StopDto();
    private StopDto to = new StopDto();
    private LocalDateTime dateTimeSearched;

    public TripRequestResponseDto(){
        trips = new ArrayList<>();
    }

    public List<TripDto> getTrips() {
        return trips;
    }

    public void setTrips(List<TripDto> trips) {
        this.trips = trips;
    }

    public StopDto getFrom() {
        return from;
    }

    public void setFrom(StopDto from) {
        this.from = from;
    }

    public StopDto getTo() {
        return to;
    }

    public void setTo(StopDto to) {
        this.to = to;
    }

    public LocalDateTime getDateTimeSearched() {
        return dateTimeSearched;
    }

    public void setDateTimeSearched(LocalDateTime dateTimeSearched) {
        this.dateTimeSearched = dateTimeSearched;
    }

}
