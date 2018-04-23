package com.stryde.webservice.service;

import com.stryde.webservice.dto.TravelRouting.StopFinderResponseDto;

import java.util.List;

public interface TravelInfoService {

    List<StopFinderResponseDto> findPossibleStops(String searchterm);

}
