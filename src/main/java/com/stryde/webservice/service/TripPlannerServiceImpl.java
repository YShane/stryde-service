package com.stryde.webservice.service;

import com.stryde.webservice.config.VRRApiConfig;
import com.stryde.webservice.dto.TravelRouting.StopFinderResponseDto;
import com.stryde.webservice.model.enums.travel.PointTypes;
import com.stryde.webservice.service.mappers.VrrResponseToStrydeResponseMappingService;
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
public class TripPlannerServiceImpl implements TripPlannerService {

    private static final Logger logger = LoggerFactory.getLogger(TripPlannerServiceImpl.class);

    @Autowired
    private VRRApiConfig apiC;

    @Autowired
    private VrrResponseToStrydeResponseMappingService mappingService;

    private final String parameterArray = "parameters";
    private final String stopFinderObject = "stopFinder";
    private final String messageArray = "message";
    private final String inputObject = "input";
    private final String pointsArray = "points";

    @Override
    public List<StopFinderResponseDto> getPossibleStopsFromApiResponse(String searchterm){

        List<StopFinderResponseDto> responseDtos = new ArrayList<>();

        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair(apiC.sessionIdparam, Integer.toString(apiC.sessionIdparamValue)));
        params.add(new BasicNameValuePair(apiC.requestIDparam, Integer.toString(apiC.requestIDparamValue)));
        params.add(new BasicNameValuePair(apiC.languageparam, apiC.languageparamValue));
        params.add(new BasicNameValuePair(apiC.coordOutputFormatparam, apiC.coordOutputFormatparamValue));
        params.add(new BasicNameValuePair(apiC.locationServerActiveparam, Integer.toString(apiC.locationServerActiveparamValue)));
        params.add(new BasicNameValuePair(apiC.statelessparam, Integer.toString(apiC.statelessparamValue)));
        params.add(new BasicNameValuePair(apiC.outputFormatparam, apiC.outputFormatparamJsonValue));

        //type in stopsearch is always any
        params.add(new BasicNameValuePair(apiC.nameparam + apiC.stopfinderAddendum, searchterm));
        params.add(new BasicNameValuePair(apiC.stoptypeparam + apiC.stopfinderAddendum, PointTypes.any.text));


        try {
            HttpClient httpClient = HttpClientBuilder.create().build();

            URIBuilder builder = new URIBuilder(apiC.urlparamValue);
            builder.setParameters(params);
            HttpGet httpGet = new HttpGet(builder.build());
            logger.debug(httpGet.toString());
            HttpResponse response = httpClient.execute(httpGet);

            logger.debug(response.toString());
            HttpEntity responseEntity = response.getEntity();

            List<StopFinderResponseDto> possibleStops = processResponse(responseEntity);

        } catch (UnsupportedEncodingException ex){
            logger.error(ex.getStackTrace().toString());
        } catch (ClientProtocolException ex2){
            logger.error(ex2.getStackTrace().toString());
        } catch (IOException ex3){
            logger.error(ex3.getStackTrace().toString());
        } catch (URISyntaxException u){
            logger.error(u.getStackTrace().toString());
        }


        return responseDtos;
    }

    private List<StopFinderResponseDto> processResponse(HttpEntity entity){

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



    private JSONObject getJSONObjectFromEntity(HttpEntity entity) throws IOException, JSONException, ParseException {

        JSONObject json = null;
        //exception is thrown if Entity is null so no point checking
        String jsonString = EntityUtils.toString(entity);
        json = new JSONObject(jsonString);

        logger.debug(json.toString());

        return json;
    }


}
