package com.stryde.webservice.dto.TravelRouting.stopfinder;

//This dto handles search terms inputted by user, carried to the backend via websockets
public class StopFinderRequestDto {

    private String searchTerm;

    private String coordinates;

    private boolean isItaCoordinate;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isItaCoordinate() {
        return isItaCoordinate;
    }

    public void setItaCoordinate(boolean itaCoordinate) {
        isItaCoordinate = itaCoordinate;
    }
}
