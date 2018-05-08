package com.stryde.webservice.dto.TravelRouting.stopfinder;

import com.stryde.webservice.dto.TravelRouting.StopDto;

public class StopFinderResponseDto {

    private StopDto point;

    public StopFinderResponseDto(){
        this.point = new StopDto();
    }
    public StopDto getPoint() {
        return point;
    }

    public void setPoint(StopDto point) {
        this.point = point;
    }
}
