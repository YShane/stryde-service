package com.stryde.webservice.dto.TravelRouting.triprequest;

import java.time.LocalDate;

public class TripInfoDto {

    private String infoType;
    private LocalDate incidentTime;
    private String infoLinkText;
    private String infoTextContent;
    private String infoTextSubtitle;
    private String infoTextSubject;
    private String infoTextAdditionalText;


    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public LocalDate getIncidentTime() {
        return incidentTime;
    }

    public void setIncidentTime(LocalDate incidentTime) {
        this.incidentTime = incidentTime;
    }

    public String getInfoLinkText() {
        return infoLinkText;
    }

    public void setInfoLinkText(String infoLinkText) {
        this.infoLinkText = infoLinkText;
    }

    public String getInfoTextContent() {
        return infoTextContent;
    }

    public void setInfoTextContent(String infoTextContent) {
        this.infoTextContent = infoTextContent;
    }

    public String getInfoTextSubtitle() {
        return infoTextSubtitle;
    }

    public void setInfoTextSubtitle(String infoTextSubtitle) {
        this.infoTextSubtitle = infoTextSubtitle;
    }

    public String getInfoTextSubject() {
        return infoTextSubject;
    }

    public void setInfoTextSubject(String infoTextSubject) {
        this.infoTextSubject = infoTextSubject;
    }

    public String getInfoTextAdditionalText() {
        return infoTextAdditionalText;
    }

    public void setInfoTextAdditionalText(String infoTextAdditionalText) {
        this.infoTextAdditionalText = infoTextAdditionalText;
    }

    @Override
    public String toString() {
        return "TripInfoDto{" +
                "infoType='" + infoType + '\'' +
                ", incidentTime=" + incidentTime +
                ", infoLinkText='" + infoLinkText + '\'' +
                ", infoTextContent='" + infoTextContent + '\'' +
                ", infoTextSubtitle='" + infoTextSubtitle + '\'' +
                ", infoTextSubject='" + infoTextSubject + '\'' +
                ", infoTextAdditionalText='" + infoTextAdditionalText + '\'' +
                '}';
    }
}
