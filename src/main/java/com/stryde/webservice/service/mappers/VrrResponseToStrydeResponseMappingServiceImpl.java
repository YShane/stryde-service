package com.stryde.webservice.service.mappers;

import com.stryde.webservice.dto.TravelRouting.StopDto;
import com.stryde.webservice.dto.TravelRouting.stopfinder.StopFinderResponseDto;
import com.stryde.webservice.exception.ApiException;
import com.stryde.webservice.exception.AppErrorCode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
     * @
     * @implNote basically turns all vrr api responses to stryde specific pojos
     */

@Service
public class VrrResponseToStrydeResponseMappingServiceImpl implements VrrResponseToStrydeResponseMappingService{

    private static final Logger LOGGER = LoggerFactory.getLogger(VrrResponseToStrydeResponseMappingServiceImpl.class);

    private final String typeAttr = "type";
    private final String nameAttr = "name";
    private final String objectAttr = "object";
    private final String statelessAttr = "stateless";
    private final String refAttr = "ref";
    private final String placeAttr = "place";
    private final String coordsAttr = "coords";
    private final String stopIdAttr = "id";
    private HashMap<Integer, String> keyset = new HashMap();

    public VrrResponseToStrydeResponseMappingServiceImpl(){
        keyset.put(1, typeAttr);
        keyset.put(2, nameAttr);
        keyset.put(3, objectAttr);
        keyset.put(4, refAttr);
        keyset.put(5, placeAttr);
        keyset.put(6, coordsAttr);
        keyset.put(7, stopIdAttr);
    }
    /**
     * @param= JSONarray
     * @implNote returns a list of (smaller) objects for the stopfinder search
     * @return {@link StopFinderResponseDto}
     */
    @Override
    public List<StopDto> getStopFinderResponses(JSONArray array){

        //array check has already been done
        List<StopDto> responseList = new ArrayList<>();

        try {
            if (array.length() > 0) {
                for (int i = 0; i < array.length(); i++) {
                    StopDto responseDto = new StopDto();
                    Object temp = array.get(i);
                    if (temp instanceof JSONObject) {
                        JSONObject currentObject = (JSONObject) temp;
                        Set k  = currentObject.keySet();
                        LOGGER.debug(currentObject.toString());

                        //Not getting and then looping through keysets to fill dtos for reasons 1: wanted Exceptions will be prevented in case the response
                        //is bad and 2: we can actually see what is going on
                        responseDto.setStopType(currentObject.getString(typeAttr));
                        responseDto.setName(currentObject.getString(nameAttr));
                        if(k.contains(this.keyset.get(3))){
                            responseDto.setObject(currentObject.getString(objectAttr));
                        }
                        Object rObject = currentObject.get(refAttr);
                        if(rObject instanceof JSONObject){
                            JSONObject refObject = (JSONObject)rObject;
                            responseDto.setStopId(refObject.getString(stopIdAttr));
                            responseDto.setPlace(refObject.getString(placeAttr));
                            responseDto.setCoordinates(refObject.getString(coordsAttr));
                        }

                        responseList.add(responseDto);
                    }
                }
            }else {
                throw new ApiException(AppErrorCode.API_RESPONSE_ERROR);
            }
        }
        catch(JSONException e){
            LOGGER.warn("Vrr JSON response discrepancies" + e);
            }

            return responseList;
    }
}
