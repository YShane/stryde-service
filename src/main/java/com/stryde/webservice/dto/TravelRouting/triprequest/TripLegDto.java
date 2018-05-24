package com.stryde.webservice.dto.TravelRouting.triprequest;

import com.stryde.webservice.dto.TravelRouting.StopDto;

import java.util.*;

public class TripLegDto {

    private Deque<Object> mitteln = new LinkedList<>();
    private List<StopDto> stopDtos;
    private int tripLegType; //0 = none, 1 = trains, 2= footpath, 3 = both

    private final String stopListKey = "stops";
    private final String footpathKey = "footpath";

    public TripLegDto(List<StopDto> stops, FootpathDto footpathDto){
        this.tripLegType = 0;
        if(stops!=null){
            hasTrains();
            this.mitteln.add(stops);
        }

        if(footpathDto!=null){
            hasFootpath();
            if(footpathDto.getPosition().equals(FootpathPosition.AFTER.text)){
                this.mitteln.add(stops);
            }else if(footpathDto.getPosition().equals(FootpathPosition.IDEST.text)){
                this.mitteln.addFirst(footpathDto);
            }
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



    private static enum FootpathPosition{
        AFTER("AFTER"), IDEST("IDEST");

        public String text;

        FootpathPosition(String text) {
            this.text = text;
        }
    }


}
