package com.stryde.webservice.service.travel;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.stryde.webservice.dto.TravelRouting.StopDto;
import com.stryde.webservice.dto.TravelRouting.triprequest.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stryde.webservice.config.VRRApiConfig;
import com.stryde.webservice.dto.TravelRouting.stopfinder.StopFinderResponseDto;
import com.stryde.webservice.model.domain.Trip;
import com.stryde.webservice.model.enums.travel.PointTypes;
import com.stryde.webservice.service.mappers.VrrResponseToStrydeResponseMappingService;
import com.stryde.webservice.utils.DateUtils;

@Service
public class TripApiServiceImpl implements TripApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripApiServiceImpl.class);

    private final String parameterArray = "parameters";
    private final String stopFinderObject = "stopFinder";
    private final String messageArray = "message";
    private final String inputObject = "input";
    private final String pointsArray = "points";


    private VRRApiConfig apiC;

    private VrrResponseToStrydeResponseMappingService mappingService;

    private ArrayList<NameValuePair> baseNameValuePairList;

    private URIBuilder baseUriBuilder;

    private HttpClient httpClient;

    @Autowired
    public TripApiServiceImpl(VRRApiConfig apiC, VrrResponseToStrydeResponseMappingService mappingService) {

        try {
            this.baseUriBuilder = new URIBuilder(apiC.urlparamValue);
        }catch (URISyntaxException u){
            LOGGER.error("UriSyntax in getTripsFromApiMethod not working" + u.getMessage());
        }

        this.httpClient = HttpClientBuilder.create().build();

        this.baseNameValuePairList = new ArrayList<>();
        baseNameValuePairList.add(new BasicNameValuePair(apiC.sessionIdparam, Integer.toString(apiC.sessionIdparamValue)));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.requestIDparam, Integer.toString(apiC.requestIDparamValue)));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.languageparam, apiC.languageparamValue));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.locationServerActiveparam, Integer.toString(apiC.locationServerActiveparamValue)));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.coordOutputFormatparam, apiC.coordOutputFormatparamValue));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.coordOutputFormatTailparam, Integer.toString(apiC.coordOutputFormatTailparamValue)));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.outputFormatparam, apiC.outputFormatparamJsonValue));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.statelessparam, Integer.toString(apiC.statelessparamValue)));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.useLocalityMainStopParam, Integer.toString(apiC.useLocalityMainStopParamValue)));

        this.apiC = apiC;
        this.mappingService = mappingService;
    }

        /**
             * @
             * @param searchterm
             * @ Returns a list of possible search results
             * @return StopFinderResponseDto
             */
    @Override
    public StopFinderResponseDto getPossibleStopsFromApi(String searchterm) throws IOException, URISyntaxException{

        ArrayList<NameValuePair> params = this.baseNameValuePairList;

        params.add(new BasicNameValuePair(apiC.nameparam + apiC.stopfinderAddendum, searchterm));
        //type in stopsearch is always any
        params.add(new BasicNameValuePair(apiC.stoptypeparam + apiC.stopfinderAddendum, PointTypes.any.text));
        params.add(new BasicNameValuePair(apiC.doNotSearchForStopsParam, Integer.toString(apiC.doNotSearchForStopsParamValue)));
        params.add(new BasicNameValuePair(apiC.anyObjFilterParam, Integer.toString(apiC.anyObjFilterParamValue)));

            String urlbase = this.baseUriBuilder.toString();

            URIBuilder uriBuilder = new URIBuilder();
            uriBuilder.setPath(urlbase + apiC.stopfinderUrl);

            uriBuilder.setParameters(params);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            LOGGER.debug(httpGet.toString());
            HttpResponse response = httpClient.execute(httpGet);

            LOGGER.debug(response.getEntity().toString());
            HttpEntity responseEntity = response.getEntity();

            return this.processStopFinderResponse(responseEntity);

    }

    private StopFinderResponseDto processStopFinderResponse(HttpEntity entity) throws IOException, JSONException, ParseException{

        StopFinderResponseDto responseDto= new StopFinderResponseDto();

        List<StopDto> stops = new ArrayList<>();

        JSONObject root = getJSONObjectFromEntity(entity);

        JSONArray parameters = root.getJSONArray(parameterArray);
        LOGGER.debug(parameters.toString());

        JSONObject stopFinderObject = root.getJSONObject(this.stopFinderObject);

        JSONArray messageArray = stopFinderObject.getJSONArray(this.messageArray);
        LOGGER.debug(messageArray.toString());

        JSONObject inputObject = stopFinderObject.getJSONObject(this.inputObject);
        LOGGER.debug(inputObject.toString());

        JSONArray pointsArray = stopFinderObject.getJSONArray(this.pointsArray);

        stops = mappingService.getStopFinderResponses(pointsArray);

        responseDto.setStopDtos(stops);

        return responseDto;

    }

    @Override
    public TripRequestResponseDto getTripsFromApi(TripRequestRequestDto requestDto) throws URISyntaxException, IOException, JSONException, ParseException{

        ArrayList<NameValuePair> params = this.baseNameValuePairList;

        params.add(new BasicNameValuePair(apiC.stoptypeparam + apiC.originAddendumParam, PointTypes.stopID.text));
        params.add(new BasicNameValuePair(apiC.nameparam + apiC.originAddendumParam, requestDto.getOrigin().getStopId()));

        params.add(new BasicNameValuePair(apiC.stoptypeparam + apiC.destinationAddendumParam, PointTypes.stopID.text));
        params.add(new BasicNameValuePair(apiC.nameparam + apiC.destinationAddendumParam, requestDto.getDestination().getStopId()));
        params.add(new BasicNameValuePair(apiC.dateParam, DateUtils.localDateTimeToDateString(requestDto.getDatetime())));
        params.add(new BasicNameValuePair(apiC.timeHourParam, Integer.toString(requestDto.getDatetime().getHour())));
        params.add(new BasicNameValuePair(apiC.timeMinuteParam, Integer.toString(requestDto.getDatetime().getMinute())));

        URIBuilder builder = this.baseUriBuilder;

        String urlbase = this.baseUriBuilder.toString();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setPath(urlbase + apiC.tripRequestUrl);
        uriBuilder.setParameters(params);
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        LOGGER.info(httpGet.toString());
        HttpResponse response = httpClient.execute(httpGet);

        LOGGER.info(response.toString());

        HttpEntity entity = response.getEntity();

        return processTripRequest(entity);
    }

    private TripRequestResponseDto processTripRequest(HttpEntity entity) throws IOException, JSONException, ParseException{

        TripRequestResponseDto response = new TripRequestResponseDto();
          final String tripsArrayKey = "trips";
          final String originobjectKey = "origin";
          final String destinationobjectKey = "destination";
          final String messageArrayKey = "itdMessageList";

            LOGGER.info(entity.toString());
            JSONObject root = getJSONObjectFromEntity(entity);

            LOGGER.info(root.toString());
            //Origin Object
            JSONObject originObject = root.getJSONObject(originobjectKey);

            //Destination Object
            JSONObject destinationObject = root.getJSONObject(destinationobjectKey);

            //Message
            JSONArray messageList = root.getJSONArray(messageArrayKey);

            JSONArray tripsArray = root.getJSONArray(tripsArrayKey);

            List<TripDto> trips = getTripsFromArray(tripsArray);

            response.setTrips(trips);
            return response;
        //TODO

    }

    //This method goes through the JSON trips array and gets the trips.
    private List<TripDto> getTripsFromArray(JSONArray tripsFromApi) throws JSONException {

        final String durationKey = "duration";
        final String interchangeKey = "interchange";
        final String legsKey = "legs";
        final String pointsKey = "points";

        List<TripDto> trips= new ArrayList<>();
        //trips array
        for(int i = 0; i< tripsFromApi.length(); i++){

            TripDto tripDto = new TripDto();

            JSONObject trip = tripsFromApi.getJSONObject(i);

            String duration = trip.getString(durationKey);
            String interchanges = trip.getString(interchangeKey);

            tripDto.setDuration(duration);
            tripDto.setUmsteige(interchanges);

            //trips have legs
            JSONArray legs = trip.getJSONArray(legsKey);

            final String stopSeqKey = "stopSeq";
            final String footpathKey = "footpath";

            List<TripLegDto> tripLegs = new ArrayList<>();
            //legs array
            for(int j = 0; j< legs.length(); j++){
                JSONObject leg = legs.getJSONObject(j);

                List<StopDto> stops= null;
                if(leg.optJSONArray(stopSeqKey)!=null){
                    LOGGER.debug("StopSeq found");
                    JSONArray stopSeq = leg.getJSONArray(stopSeqKey);

                    stops = this.mappingService.getStopSequence(stopSeq);
                }

                FootpathDto footpathDto = null;
                if(leg.optJSONArray(footpathKey)!=null){
                    LOGGER.debug("Footpath found");
                    //In this case, there's a footpath. Doesn't exclude any other means of transportation

                    JSONArray footpathArray =  leg.getJSONArray(footpathKey);
                    //There's always a JSON Object in the Array
                    JSONObject footpathObject = footpathArray.getJSONObject(0);
                    final String footpathAfterBeforeOrISKey = "position"; //does the footpath come before or after the included stopseq? Or is it just a leg with only a footpath?
                    footpathDto = new FootpathDto();
                    String footpathPosition = footpathObject.getString(footpathAfterBeforeOrISKey);
                    footpathDto.setPosition(footpathPosition);
                }
                TripLegDto tripLegDto = new TripLegDto(stops, footpathDto);
                tripLegs.add(tripLegDto);
            }
            tripDto.setTripLegs(tripLegs);
            trips.add(tripDto);
        }
        return trips;
    }

    private TripMessage getMessage(JSONArray messageArray){

        TripMessage message = new TripMessage();
        message.setMessage("placeholder");
        return message;
    }


    private JSONObject getJSONObjectFromEntity(HttpEntity entity) throws IOException, JSONException, ParseException {

        JSONObject json = null;
        //exception is thrown if Entity is null so no point checking
        String jsonString = EntityUtils.toString(entity);
        json = new JSONObject(jsonString);

        LOGGER.debug(json.toString());

        return json;
    }



}
