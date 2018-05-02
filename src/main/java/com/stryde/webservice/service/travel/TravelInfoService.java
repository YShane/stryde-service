package com.stryde.webservice.service.travel;

import com.stryde.webservice.dto.TravelRouting.stopfinder.StopFinderResponseDto;
import com.stryde.webservice.dto.TravelRouting.triprequest.TripRequestRequestDto;
import com.stryde.webservice.model.domain.Trip;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

public interface TravelInfoService {

    List<StopFinderResponseDto> findStops(String searchterm) throws IOException, URISyntaxException;

    List<Trip> findTrips(TripRequestRequestDto requestDto);

}
