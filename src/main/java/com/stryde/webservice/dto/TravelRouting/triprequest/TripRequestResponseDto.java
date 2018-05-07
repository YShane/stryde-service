package com.stryde.webservice.dto.TravelRouting.triprequest;

import java.time.LocalDateTime;
import java.util.List;

public class TripRequestResponseDto {

    private List<TripDto> trips;

    private String seachedTerm;

    private LocalDateTime dateTimeSearched;

    private TripInfoDto originInfoDto;

    private TripInfoDto destinationInfoDto;





}
