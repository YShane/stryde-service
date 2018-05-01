package com.stryde.webservice.model.domain;

import javax.persistence.*;


@Entity
@Table(name = "stop")
public class Stop {

    private static final String C_Id = "id";
    private static final String C_stopId = "stopId";
    private static final String C_name = "name";
    private static final String C_coordinates = "coordinates";
    private static final String C_type = "type";
    private static final String C_place = "place";
    private static final String C_placeId = "placeId";

    @Column(name= C_Id, nullable = false)@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = C_stopId)
    private Long stopId;

    @Column(name = C_name)
    private String name;

    @Column(name = C_place)
    private String place;

    @Column(name = C_placeId)
    private Long placeId;

    @Column(name = C_coordinates)
    private String coordinates;

    @Column(name = C_type)
    private String stopType;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getStopId() {
        return stopId;
    }

    public void setStopId(Long stopId) {
        this.stopId = stopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public String getStopType() {
        return stopType;
    }

    public void setStopType(String stopType) {
        this.stopType = stopType;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
}

