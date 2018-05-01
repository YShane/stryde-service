package com.stryde.webservice.dto.TravelRouting.triprequest;

import java.time.LocalDateTime;

public class SaveTripRequestDto {

    private LocalDateTime boardTime;
    private LocalDateTime alightTime;
    private int duration;
    private int numberOfChanges;

    private String line;
    private String trainName;
    private String trainNumber;
    private String trainSymbol;
    private String product;
    private int productId;
    private int mittelType;
    private String tripCode;
    private String operator;
    private String network;
    private String project;
    private String stateless;
    private String destinationId;

    public LocalDateTime getBoardTime() {
        return boardTime;
    }

    public void setBoardTime(LocalDateTime boardTime) {
        this.boardTime = boardTime;
    }

    public LocalDateTime getAlightTime() {
        return alightTime;
    }

    public void setAlightTime(LocalDateTime alightTime) {
        this.alightTime = alightTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNumberOfChanges() {
        return numberOfChanges;
    }

    public void setNumberOfChanges(int numberOfChanges) {
        this.numberOfChanges = numberOfChanges;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainSymbol() {
        return trainSymbol;
    }

    public void setTrainSymbol(String trainSymbol) {
        this.trainSymbol = trainSymbol;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMittelType() {
        return mittelType;
    }

    public void setMittelType(int mittelType) {
        this.mittelType = mittelType;
    }

    public String getTripCode() {
        return tripCode;
    }

    public void setTripCode(String tripCode) {
        this.tripCode = tripCode;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getStateless() {
        return stateless;
    }

    public void setStateless(String stateless) {
        this.stateless = stateless;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }
}
