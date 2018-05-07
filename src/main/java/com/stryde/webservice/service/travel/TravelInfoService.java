package com.stryde.webservice.service.travel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.stryde.webservice.dto.TravelRouting.stopfinder.StopFinderResponseDto;
import com.stryde.webservice.dto.TravelRouting.triprequest.TripRequestRequestDto;
import com.stryde.webservice.model.domain.Trip;

public interface TravelInfoService {

    List<StopFinderResponseDto> findStops(String searchterm) throws IOException, URISyntaxException;

    List<Trip> findTrips(TripRequestRequestDto requestDto);

}
