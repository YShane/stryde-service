package com.stryde.webservice.service.travel;

import com.stryde.webservice.dto.TravelRouting.stopfinder.StopFinderResponseDto;
import com.stryde.webservice.dto.TravelRouting.triprequest.TripRequestRequestDto;
import com.stryde.webservice.model.domain.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelInfoServiceImpl implements TravelInfoService {

    private static final Logger logger = LoggerFactory.getLogger(TravelInfoServiceImpl.class);

    @Autowired
    private TripApiService tripApiService;

    @Override
    public List<StopFinderResponseDto> findStops(String searchterm) {

      return this.tripApiService.getPossibleStopsFromApiResponse(searchterm);
    }

    public List<Trip> findTrips(TripRequestRequestDto requestDto){
        return null;

    }
}
