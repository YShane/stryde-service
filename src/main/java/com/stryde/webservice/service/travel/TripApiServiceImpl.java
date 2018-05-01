package com.stryde.webservice.service.travel;

import com.stryde.webservice.config.VRRApiConfig;
import com.stryde.webservice.dto.TravelRouting.stopfinder.StopFinderResponseDto;
import com.stryde.webservice.dto.TravelRouting.triprequest.TripRequestRequestDto;
import com.stryde.webservice.dto.TravelRouting.triprequest.TripRequestResponseDto;
import com.stryde.webservice.model.domain.Trip;
import com.stryde.webservice.model.enums.travel.PointTypes;
import com.stryde.webservice.service.mappers.VrrResponseToStrydeResponseMappingService;
import com.stryde.webservice.utils.DateUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripApiServiceImpl implements TripApiService {

    private static final Logger logger = LoggerFactory.getLogger(TripApiServiceImpl.class);

    @Autowired
    private VRRApiConfig apiC;

    @Autowired
    private VrrResponseToStrydeResponseMappingService mappingService;

    private ArrayList<NameValuePair> baseNameValuePairList;

    private URIBuilder baseUriBuilder = new URIBuilder();


    public TripApiServiceImpl(VRRApiConfig apiC) {

        try {
            this.baseUriBuilder = new URIBuilder(apiC.urlparamValue);
        }catch (URISyntaxException u){
            logger.error("UriSyntax in getTripsFromApiMethod not working" + u.getMessage());
        }


        this.baseNameValuePairList = new ArrayList<NameValuePair>();
        baseNameValuePairList.add(new BasicNameValuePair(apiC.sessionIdparam, Integer.toString(apiC.sessionIdparamValue)));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.requestIDparam, Integer.toString(apiC.requestIDparamValue)));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.languageparam, apiC.languageparamValue));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.locationServerActiveparam, Integer.toString(apiC.locationServerActiveparamValue)));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.coordOutputFormatparam, apiC.coordOutputFormatparamValue));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.coordOutputFormatTailparam, Integer.toString(apiC.coordOutputFormatTailparamValue)));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.outputFormatparam, apiC.outputFormatparamJsonValue));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.statelessparam, Integer.toString(apiC.statelessparamValue)));
        baseNameValuePairList.add(new BasicNameValuePair(apiC.useLocalityMainStopParam, Integer.toString(apiC.useLocalityMainStopParamValue)));

    }

    private final String parameterArray = "parameters";
    private final String stopFinderObject = "stopFinder";
    private final String messageArray = "message";
    private final String inputObject = "input";
    private final String pointsArray = "points";

        /**
             * @
             * @param searchterm
             * @ Returns a list of possible search results
             * @return StopFinderResponseDto
             */
    @Override
    public List<StopFinderResponseDto> getPossibleStopsFromApiResponse(String searchterm){

        List<StopFinderResponseDto> responseDtos = new ArrayList<>();

        ArrayList<NameValuePair> params = this.baseNameValuePairList;


        params.add(new BasicNameValuePair(apiC.nameparam + apiC.stopfinderAddendum, searchterm));
        //type in stopsearch is always any
        params.add(new BasicNameValuePair(apiC.stoptypeparam + apiC.stopfinderAddendum, PointTypes.any.text));
        params.add(new BasicNameValuePair(apiC.doNotSearchForStopsParam, Integer.toString(apiC.doNotSearchForStopsParamValue)));
        params.add(new BasicNameValuePair(apiC.anyObjFilterParam, Integer.toString(apiC.anyObjFilterParamValue)));

        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            URIBuilder builder = this.baseUriBuilder;
            String basepath = builder.getPath();

            builder.setPath(basepath + apiC.stopfinderurlparamValue);

            builder.setParameters(params);
            HttpGet httpGet = new HttpGet(builder.build());
            logger.debug(httpGet.toString());
            HttpResponse response = httpClient.execute(httpGet);

            logger.debug(response.toString());
            HttpEntity responseEntity = response.getEntity();

            List<StopFinderResponseDto> possibleStops = processStopFinderResponse(responseEntity);

        } catch (UnsupportedEncodingException ex){
            logger.error(ex.getStackTrace().toString());
        } catch (ClientProtocolException ex2){
            logger.error(ex2.getStackTrace().toString());
        } catch (IOException ex3){
            logger.error(ex3.getStackTrace().toString());
        }catch (URISyntaxException u){
            logger.error("URI syntax issue when building" + u.getMessage());
        }

        return responseDtos;
    }

    public List<Trip> getTripsFromApi(TripRequestRequestDto requestDto){

        List<Trip> possibleTrips = new ArrayList<>();

        ArrayList<NameValuePair> params = this.baseNameValuePairList;

        params.add(new BasicNameValuePair(apiC.stoptypeparam + apiC.originAddendumParam, PointTypes.stopID.text));
        params.add(new BasicNameValuePair(apiC.nameparam + apiC.originAddendumParam, requestDto.getOrigin().getStopId()));

        params.add(new BasicNameValuePair(apiC.stoptypeparam + apiC.destinationAddendumParam, PointTypes.stopID.text));
        params.add(new BasicNameValuePair(apiC.nameparam + apiC.destinationAddendumParam, requestDto.getDestination().getStopId()));
        params.add(new BasicNameValuePair(apiC.dateParam, DateUtils.localDateTimeToDateString(requestDto.getdateTime())));
        params.add(new BasicNameValuePair(apiC.timeHourParam, Integer.toString(requestDto.getdateTime().getHour())));
        params.add(new BasicNameValuePair(apiC.timeMinuteParam, Integer.toString(requestDto.getdateTime().getMinute())));

        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder builder = this.baseUriBuilder;

        String basepath = builder.getPath();
        builder.setPath(basepath + apiC.tripRequestUrlParam);

        try {
            HttpGet httpGet = new HttpGet(builder.build());
            HttpResponse response = httpClient.execute(httpGet);
            logger.debug(response.toString());

            HttpEntity entity = response.getEntity();

            TripRequestResponseDto responseDto = processTripRequest(entity);



        }catch (URISyntaxException u){
            logger.error("URI syntax issue when building URL" + u.getMessage());
        }catch (ClientProtocolException c){
            logger.error("Error in executing Http Get Request: " + c);
        }catch (IOException i){
            logger.error("IOException :" + i);
        }

        return null;
    }

    private List<StopFinderResponseDto> processStopFinderResponse(HttpEntity entity){

        List<StopFinderResponseDto> responseDtos = new ArrayList<>();

        try {
            JSONObject root = getJSONObjectFromEntity(entity);

            JSONArray parameters = root.getJSONArray(parameterArray);
            logger.debug(parameters.toString());

            JSONObject stopFinderObject = root.getJSONObject(this.stopFinderObject);

            JSONArray messageArray = stopFinderObject.getJSONArray(this.messageArray);
            logger.debug(messageArray.toString());

            JSONObject inputObject = stopFinderObject.getJSONObject(this.inputObject);
            logger.debug(inputObject.toString());

            JSONArray pointsArray = stopFinderObject.getJSONArray(this.pointsArray);

            responseDtos = mappingService.getStopFinderResponses(pointsArray);

        }catch (JSONException e){
            //syntax error in json string. Should probably have already been a parse exception
            logger.error(e.toString());
        }catch (ParseException e1){
            //probably not json
            logger.error(e1.toString());
        }catch (IOException e2){
            //error reading json
            logger.error(e2.toString());
        }
        return responseDtos;
    }

    private TripRequestResponseDto processTripRequest(HttpEntity entity){
        try {
            JSONObject object = getJSONObjectFromEntity(entity);

        }catch (IOException i){
            logger.error("IOException when getting JSON from HttpEntity" + i);
        }
        //TODO
        return null;
    }
    private JSONObject getJSONObjectFromEntity(HttpEntity entity) throws IOException, JSONException, ParseException {

        JSONObject json = null;
        //exception is thrown if Entity is null so no point checking
        String jsonString = EntityUtils.toString(entity);
        json = new JSONObject(jsonString);

        logger.debug(json.toString());

        return json;
    }




}
