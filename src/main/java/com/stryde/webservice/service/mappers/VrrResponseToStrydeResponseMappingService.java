package com.stryde.webservice.service.mappers;

import com.stryde.webservice.dto.TravelRouting.StopDto;
import com.stryde.webservice.dto.TravelRouting.stopfinder.StopFinderResponseDto;
import org.json.JSONArray;

import java.util.List;

public interface VrrResponseToStrydeResponseMappingService {

    //used in the stopfinder request
    List<StopDto> getStopFinderResponses(JSONArray array);

    //used in the triprequest
    List<StopDto> getStopSequence(JSONArray stopSeqArray);
}
