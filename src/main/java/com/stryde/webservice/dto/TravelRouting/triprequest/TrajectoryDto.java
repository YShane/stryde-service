package com.stryde.webservice.dto.TravelRouting.triprequest;

import java.time.LocalDate;

public class TrajectoryDto {


    private Long Id;

    //e.g R-Bahn RE1
    private String trainName;

    //e.g RE1
    private String trainNumber;

    //e.g RE1
    private String trainSymbol;

    //e.g R-Bahn
    private String product;

    private int productId;

    private int mittelType;

    private String tripCode;

    //e.g 90
    private String branch;

    private String line;

    //e.g DB AG
    private String operator;

    private String network;

    private String project;

    private String stateless;

    private String destinationId;

    private LocalDate trajectoryDate;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        this.Id = id;
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

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
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

    public LocalDate getTrajectoryDate() {
        return trajectoryDate;
    }

    public void setTrajectoryDate(LocalDate trajectoryDate) {
        this.trajectoryDate = trajectoryDate;
    }
}
