package com.stryde.webservice.dto.TravelRouting.triprequest;

import com.stryde.webservice.dto.TravelRouting.StopDto;

import java.util.ArrayList;
import java.util.List;

public class TripLegDto extends AbstractTripLegDto{

    private List<StopDto> stopDtos;

    public List<StopDto> getStopDtos() {
        if(this.stopDtos==null){
            return new ArrayList<StopDto>();
        }else {
            return stopDtos;
        }
    }

    public void setStopDtos(List<StopDto> stopDtos) {
        this.stopDtos = stopDtos;
    }
}
