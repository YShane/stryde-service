package com.stryde.webservice.service.mappers;

import com.stryde.webservice.dto.TravelRouting.StopDto;
import com.stryde.webservice.dto.TravelRouting.stopfinder.StopFinderResponseDto;
import org.json.JSONArray;

import java.util.List;

public interface VrrResponseToStrydeResponseMappingService {

    List<StopDto> getStopFinderResponses(JSONArray array);
}
