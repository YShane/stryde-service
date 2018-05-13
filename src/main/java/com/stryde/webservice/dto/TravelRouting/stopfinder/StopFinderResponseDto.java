package com.stryde.webservice.dto.TravelRouting.stopfinder;

import com.stryde.webservice.dto.TravelRouting.StopDto;

import java.util.ArrayList;
import java.util.List;

public class StopFinderResponseDto {

    private List<StopDto> stopDtos;


    public StopFinderResponseDto(){
        this.stopDtos = new ArrayList<StopDto>();
    }

    public List<StopDto> getStopDtos() {
        return stopDtos;
    }

    public void setStopDtos(List<StopDto> stopDtos) {
        this.stopDtos = stopDtos;
    }
}
