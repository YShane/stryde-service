package com.stryde.webservice.model.enums.travel;

public enum PointTypes {
    poiID("poiID"), stopID("stopID"), coord("coord"), any("any");

    public String text;

    PointTypes(String text) {
        this.text = text;
    }
}
