package com.stryde.webservice.service.mappers;

import com.stryde.webservice.dto.TravelRouting.stopfinder.StopFinderResponseDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    private final String refAttr = "ref";
    private final String placeAttr = "place";
    private final String coordsAttr = "coords";


    /**
     * @param= JSONarray
     * @implNote returns a list of (smaller) objects for the stopfinder search
     * @return {@link StopFinderResponseDto}
     */
    @Override
    public List<StopFinderResponseDto> getStopFinderResponses(JSONArray array){

        //array check has already been done

        List<StopFinderResponseDto> responseList = new ArrayList<>();

        if(array.length()>0){

            for(int i=0; i<array.length();i++){

                StopFinderResponseDto responseDto = new StopFinderResponseDto();

                Object temp = array.get(i);

                if(temp instanceof JSONObject){
                    JSONObject currentObject = (JSONObject)temp;
                    LOGGER.debug(currentObject.toString());
                    responseDto.getPoint().setStopType(currentObject.getString(typeAttr));
                    responseDto.getPoint().setName(currentObject.getString(nameAttr));
                    responseDto.getPoint().setObject(currentObject.getString(objectAttr));

                    JSONObject refObject = currentObject.getJSONObject(refAttr);
                    responseDto.getPoint().setPlace(refObject.getString(placeAttr));
                    responseDto.getPoint().setCoordinates(refObject.getString(coordsAttr));

                    responseList.add(responseDto);
                }

            }
        } else {

        }
       return responseList;
    }
}
