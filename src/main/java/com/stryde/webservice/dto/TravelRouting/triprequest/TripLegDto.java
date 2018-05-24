package com.stryde.webservice.dto.TravelRouting.triprequest;

import com.stryde.webservice.dto.TravelRouting.StopDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TripLegDto {

    private HashMap<Integer, Object> mitteln;
    private List<StopDto> stopDtos;
    private int tripLegType; //0 = none, 1 = trains, 2= footpath, 3 = both


    TripLegDto(List<StopDto> stops, FootpathDto footpathDto){
        this.tripLegType = 0;
        if(stops!=null){
            hasTrains();
        }
        if(footpathDto!=null){
            hasFootpath();
        }
        if(footpathDto.getPosition().equals(FootpathPosition.AFTER.text)){

        }
    }

    public List<StopDto> getStopDtos() {
        return this.stopDtos;
    }

    public void setStopDtos(List<StopDto> stopDtos) {
        this.stopDtos = stopDtos;
    }

   private void hasTrains(){
        this.tripLegType  = this.tripLegType + 1;
    }
    private void hasFootpath(){
        this.tripLegType = this.tripLegType + 2;
    }

    public int getTripLegType() {
        return tripLegType;
    }

    public void legArrangement(){
        if(){

        }
    }

    private static enum FootpathPosition{
        AFTER("AFTER"), IDEST("IDEST");

        public String text;

        FootpathPosition(String text) {
            this.text = text;
        }
    }


}
