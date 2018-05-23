package com.stryde.webservice.dto.TravelRouting.triprequest;

public abstract class AbstractTripLegDto{

    //1 = train/bus,   2 = footpath,  3 = both
    private int tripLegType;

    public int getTripLegType() {
        return tripLegType;
    }

    public void setTripLegType(int tripLegType) {
        this.tripLegType = tripLegType;
    }
}
