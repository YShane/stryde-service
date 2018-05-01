package com.stryde.webservice.service.travel;

import com.stryde.webservice.dto.TravelRouting.stopfinder.StopFinderResponseDto;
import com.stryde.webservice.dto.TravelRouting.triprequest.TripRequestRequestDto;
import com.stryde.webservice.model.domain.Trip;

import java.util.List;

public interface TripApiService {

    List<StopFinderResponseDto> getPossibleStopsFromApiResponse(String searchterm);

    List<Trip> getTripsFromApi(TripRequestRequestDto requestDto);

}
