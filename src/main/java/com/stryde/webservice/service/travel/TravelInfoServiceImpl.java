package com.stryde.webservice.service.travel;

import com.stryde.webservice.dto.TravelRouting.stopfinder.StopFinderResponseDto;
import com.stryde.webservice.dto.TravelRouting.triprequest.TripRequestRequestDto;
import com.stryde.webservice.dto.TravelRouting.triprequest.TripRequestResponseDto;
import com.stryde.webservice.model.domain.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Service
public class TravelInfoServiceImpl implements TravelInfoService {

    private static final Logger logger = LoggerFactory.getLogger(TravelInfoServiceImpl.class);

    @Autowired
    private TripApiService tripApiService;

    @Override
    public StopFinderResponseDto findStops(String searchterm) throws IOException, URISyntaxException {

      return this.tripApiService.getPossibleStopsFromApi(searchterm);
    }

    public TripRequestResponseDto findTrips(TripRequestRequestDto requestDto) throws IOException, URISyntaxException{
        return this.tripApiService.getTripsFromApi(requestDto);
    }
}
