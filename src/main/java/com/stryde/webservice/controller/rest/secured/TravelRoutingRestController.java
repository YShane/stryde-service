package com.stryde.webservice.controller.rest.secured;

import com.stryde.webservice.dto.TravelRouting.TravelRouteDto;
import com.stryde.webservice.dto.TravelRouting.TravelSearchDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travel")
public class TravelRoutingRestController {

    @MessageMapping("/search")
    @SendToUser
    public void searchForSpot(TravelSearchDto spot) {

    }

    //User adds a trip for which he's willing to take someone with him
    @RequestMapping("/addtrip")
    public void addTripToTake(TravelRouteDto travelRouteDto){

    }
}
