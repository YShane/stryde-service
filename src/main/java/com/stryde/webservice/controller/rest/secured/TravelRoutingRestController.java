package com.stryde.webservice.controller.rest.secured;

import com.stryde.webservice.dto.TravelRouting.StopFinderResponseDto;
import com.stryde.webservice.dto.TravelRouting.TravelSearchDto;
import com.stryde.webservice.service.TravelInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/travel")
public class TravelRoutingRestController {

    @Autowired
    private TravelInfoService travelInfoService;



    @RequestMapping("/findStop")
    public ResponseEntity<?> searchForSpot(HttpServletRequest request, TravelSearchDto spot) {
        try {
            this.travelInfoService.findPossibleStops(spot.getSearchTerm());
            return new ResponseEntity<>(this.travelInfoService.findPossibleStops(spot.getSearchTerm()), HttpStatus.OK);
        } catch (Exception x){
            //TODO: Exceptions should be handled here
            return new ResponseEntity<String>("Search Failed", HttpStatus.BAD_REQUEST);
        }

    }
/*
    //User adds a trip for which he's willing to take someone with him
    @RequestMapping("/addtrip")
    public void addTripToTake(TravelRouteDto travelRouteDto){

    }*/
}
