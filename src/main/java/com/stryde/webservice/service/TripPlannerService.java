package com.stryde.webservice.service;

import com.stryde.webservice.dto.TravelRouting.StopFinderResponseDto;
import org.apache.http.HttpEntity;

import java.util.List;

public interface TripPlannerService {

    List<StopFinderResponseDto> getPossibleStopsFromApiResponse(String searchterm);
}
