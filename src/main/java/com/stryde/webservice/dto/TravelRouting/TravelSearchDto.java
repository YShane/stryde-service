package com.stryde.webservice.dto.TravelRouting;

//This dto handles search terms inputted by user, carried to the backend via websockets
public class TravelSearchDto {

    private String searchTerm;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
}
