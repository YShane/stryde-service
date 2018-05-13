package com.stryde.webservice.service.travel;

import com.stryde.webservice.dto.TravelRouting.StopDto;
import com.stryde.webservice.dto.TravelRouting.stopfinder.StopFinderResponseDto;
import com.stryde.webservice.dto.TravelRouting.triprequest.TripRequestRequestDto;
import com.stryde.webservice.dto.TravelRouting.triprequest.TripRequestResponseDto;
import com.stryde.webservice.model.domain.Trip;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface TripApiService {

    StopFinderResponseDto getPossibleStopsFromApi(String searchterm) throws ClientProtocolException, URISyntaxException,IOException;

    TripRequestResponseDto getTripsFromApi(TripRequestRequestDto requestDto) throws URISyntaxException, IOException, JSONException, ParseException;

}
