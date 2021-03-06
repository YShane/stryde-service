package com.stryde.webservice.dto.TravelRouting;

public class StopDto {

    private String stopId;

    //the place is the name of the city
    private String place;
    //the object is the name of the exact place(stop)
    private String object;
    //the name is the combination of the place and the object
    private String name;

    private String coordinates;
    private String stopType;
    private String pointGid;

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
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

    public String getPointGid() {
        return pointGid;
    }

    public void setPointGid(String pointGid) {
        this.pointGid = pointGid;
    }

    @Override
    public String toString() {
        return "StopDto{" +
                "stopId='" + stopId + '\'' +
                ", place='" + place + '\'' +
                ", object='" + object + '\'' +
                ", name='" + name + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", stopType='" + stopType + '\'' +
                ", pointGid='" + pointGid + '\'' +
                '}';
    }
}
