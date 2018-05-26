package com.stryde.webservice.dto.TravelRouting.triprequest;

import com.stryde.webservice.dto.TravelRouting.StopDto;

import java.util.*;

//This class represents the "umstiege" that a person makes. If you're travelling from point A to C via point b taking 2 traisn respectively, each trip leg is a triplegObject.
//Since footpaths aren't really to be saved, we either place them before or after the "train" legs, both in the mitteln list
public class TripLegDto {

    private Deque<Object> mitteln = new LinkedList<>();
    private int tripLegType; //0 = none, 1 = trains, 2= footpath, 3 = both; Bitmask
    private String footpathPosition;

    public TripLegDto(List<StopDto> stops, FootpathDto footpathDto){
        this.tripLegType = 0;
        if(stops!=null){
            hasTrains();
            this.mitteln.add(stops);
        }

        if(footpathDto!=null){
            hasFootpath();
            if(footpathDto.getPosition().equals(FootpathPositionEnum.AFTER.text)){
                this.mitteln.add(stops);
                this.footpathPosition = FootpathPositionEnum.AFTER.text;
            }else if(footpathDto.getPosition().equals(FootpathPositionEnum.IDEST.text)){
                this.mitteln.addFirst(footpathDto);
                this.footpathPosition = FootpathPositionEnum.IDEST.text;
            }
        }
    }

    public Deque<Object> getMitteln() {
        return mitteln;
    }

    public void setMitteln(Deque<Object> mitteln) {
        this.mitteln = mitteln;
    }

    public void setTripLegType(int tripLegType) {
        this.tripLegType = tripLegType;
    }

    public List<StopDto> getStops(){
        if(tripLegType==1){
            return (List<StopDto>) this.mitteln.getFirst();
        }else if(tripLegType==3){
            if(footpathPosition.equals(FootpathPositionEnum.AFTER.text)){
                return (List<StopDto>)this.mitteln.getFirst();
            }else if(footpathPosition.equals(FootpathPositionEnum.IDEST.text)){
                return (List<StopDto>)this.mitteln.getLast();
            }else{
                return null;
            }
        }else{
            return null;
        }
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



    private enum FootpathPositionEnum {
        AFTER("AFTER"), IDEST("IDEST");

        public String text;

        FootpathPositionEnum(String text) {
            this.text = text;
        }
    }


}
