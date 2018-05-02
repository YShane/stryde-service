package com.stryde.webservice.controller.rest.secured;

import com.stryde.webservice.dto.TravelRouting.stopfinder.StopFinderRequestDto;
import com.stryde.webservice.dto.TravelRouting.triprequest.TripRequestRequestDto;
import com.stryde.webservice.service.travel.TravelInfoService;
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


    @PreAuthorize("@securityService.isMyAccount(#authentication, #userDto.userId)")
    @PostMapping("/findStop")
    public ResponseEntity<?> searchForSpot(@Valid @RequestBody StopFinderRequestDto spot) throws IOException, URISyntaxException {

            return new ResponseEntity<>(this.travelInfoService.findStops(spot.getSearchTerm()), HttpStatus.OK);
    }

    public ResponseEntity<?> doTripRequest(HttpServletRequest request, TripRequestRequestDto requestDto){

        return null;

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
