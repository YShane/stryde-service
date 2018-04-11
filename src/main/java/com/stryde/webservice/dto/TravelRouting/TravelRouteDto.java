package com.stryde.webservice.dto.TravelRouting;

//This handles user inputted start, and stop locations, optionally, time of travel
public class TravelRouteDto {

    private String startLocationName;
    private String endLocationName;
    private String startTime;

    public String getStartLocationName() {
        return startLocationName;
    }

    public void setStartLocationName(String startLocationName) {
        this.startLocationName = startLocationName;
    }

    public String getEndLocationName() {
        return endLocationName;
    }

    public void setEndLocationName(String endLocationName) {
        this.endLocationName = endLocationName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
