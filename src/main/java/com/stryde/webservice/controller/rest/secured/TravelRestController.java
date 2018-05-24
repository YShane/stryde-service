package com.stryde.webservice.controller.rest.secured;

import com.stryde.webservice.dto.TravelRouting.stopfinder.StopFinderRequestDto;
import com.stryde.webservice.dto.TravelRouting.triprequest.TripRequestRequestDto;
import com.stryde.webservice.service.travel.TravelInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/travel")
public class TravelRestController {

    @Autowired
    private TravelInfoService travelInfoService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TravelRestController.class);


    @PreAuthorize("@securityService.isMyAccount(#authentication, #userDto.userId)")
    @PostMapping("/findstops")
    public ResponseEntity<?> searchForSpot(@Valid @RequestBody StopFinderRequestDto spot) throws IOException, URISyntaxException {

            return new ResponseEntity<>(this.travelInfoService.findStops(spot.getSearchTerm()), HttpStatus.OK);
    }

    @PreAuthorize("@securityService.isMyAccount(#authentication, #userDto.userId)")
    @PostMapping("/findtrips")
    public ResponseEntity<?> doTripRequest(@Valid @RequestBody TripRequestRequestDto requestDto) throws IOException, URISyntaxException {
        LOGGER.info(requestDto.toString());
        return new ResponseEntity<>(travelInfoService.findTrips(requestDto), HttpStatus.OK);
    }

    /**
     * @implNote trip info from api is only saved after this method is called
     * @param
     * @
     * @return {@link com.stryde.webservice.dto}
     */
    @PreAuthorize("@securityService.isMyAccount(#authentication, #userDto.userId)")
    @PostMapping("/savetrip")
    public ResponseEntity<?> saveTripImTaking(){
        return null;
    }


/*
    //User adds a trip for which he's willing to take someone with him
    @RequestMapping("/addtrip")
    public void addTripToTake(TravelRouteDto travelRouteDto){

    }*/

}
