package com.stryde.webservice.service;

import com.stryde.webservice.dto.TravelRouting.StopFinderResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelInfoServiceImpl implements TravelInfoService {

    private static final Logger logger = LoggerFactory.getLogger(TravelInfoServiceImpl.class);

    @Autowired
    private TripPlannerService tripPlannerService;



    @Override
    public List<StopFinderResponseDto> findPossibleStops(String searchterm) {

      return this.tripPlannerService.getPossibleStopsFromApiResponse(searchterm);
    }
}
