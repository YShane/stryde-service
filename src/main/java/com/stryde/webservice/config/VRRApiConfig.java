package com.stryde.webservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:external/vrrapi.properties")
public class VRRApiConfig {

    //This class really should be rewritten/refactored

    @Value("${urlparam}")
    public String urlparamValue;


    @Value("${languageparam}")
    public String languageparam;
    @Value("${language}")
    public String languageparamValue;


    @Value("${sessionIdparam}")
    public String sessionIdparam;
    @Value("${sessionId}")
    public int sessionIdparamValue;


    @Value("${requestIDparam}")
    public String requestIDparam;
    @Value("${requestID}")
    public int requestIDparamValue;


    @Value("${outputFormatparam}")
    public String outputFormatparam;
    @Value("${outputFormatXml}")
    public String outputFormatparamXmlValue;
    @Value("${outputFormatJson}")
    public String outputFormatparamJsonValue;


    @Value("${coordListOutputFormatparam}")
    public String coordListOutputFormatparam;
    @Value("${coordListOutputFormat}")
    public String coordListOutputFormatparamValue;


    @Value("${coordOutputFormatparam}")
    public String coordOutputFormatparam;
    @Value("${coordOutputFormat}")
    public String coordOutputFormatparamValue;


    @Value("${coordOutputFormatTailparam}")
    public String coordOutputFormatTailparam;
    @Value("${coordOutputFormatTail}")
    public int coordOutputFormatTailparamValue;

    @Value("${useLocalityMainStopParam}")
    public String useLocalityMainStopParam;
    @Value("${useLocalityMainStopParamValue}")
    public int useLocalityMainStopParamValue;

    @Value("${doNotSearchForStopsParam}")
    public String doNotSearchForStopsParam;
    @Value("${doNotSearchForStopsParamValue}")
    public int doNotSearchForStopsParamValue;

    @Value("${anyObjFilterParam}")
    public String anyObjFilterParam;
    @Value("${anyObjFilterParamValue}")
    public int anyObjFilterParamValue;

    @Value("${locationServerActiveparam}")
    public String locationServerActiveparam;
    @Value("${locationServerActive}")
    public int locationServerActiveparamValue;

    @Value("${statelessparam}")
    public String statelessparam;
    @Value("${stateless}")
    public int statelessparamValue;

    @Value("${execInstparam}")
    public String execInstparam;
    @Value("${execInst}")
    public String execInstparamValue;


    @Value("${UTFMacroparam}")
    public String UTFMacroparam;
    @Value("${UTFMacro}")
    public String UTFMacroparamValue;


    @Value("${stoptypeparam}")
    public String stoptypeparam;

    @Value("${nameparam}")
    public String nameparam;

    @Value("${placeparam}")
    public String placeParam;

    @Value("${itdDateDayMonthYearparam}")
    public String dateParam;

    @Value("${itdTimeHourparam}")
    public String timeHourParam;

    @Value("${itdTimeMinutepram}")
    public String timeMinuteParam;



    //request specific urls
    @Value("${stopfinderurl}")
    public String stopfinderUrl;

    //Stopfinderaddendum at end of certain params
    @Value("${stopfinderaddendum}")
    public String stopfinderAddendum;

/**/

    //TripRequest
    @Value("${triprequesturl}")
    public String tripRequestUrl;

    @Value("${TRoriginaddendum}")
    public String originAddendumParam;

    @Value("${TRdestinationaddendum}")
    public String destinationAddendumParam;

    @Value("${TRviaaddendum}")
    public String viaAddendumParam;

    @Value("${istimedepartureorarrivaltimeparam}")
    public String isTimeDepartureOrArrivalParam;



//    @Value("${}")




}
