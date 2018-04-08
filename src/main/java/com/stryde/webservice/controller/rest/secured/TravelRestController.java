package com.stryde.webservice.controller.rest.secured;

import com.stryde.webservice.dto.TravelRouteDto;
import com.stryde.webservice.dto.TravelRouteDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travel")
public class TravelRestController {

    //User adds a trip for which he's willing to take someone with him
    @RequestMapping("/addtrip")
    public void addTripToTake(TravelRouteDto travelRouteDto){

    }
}
