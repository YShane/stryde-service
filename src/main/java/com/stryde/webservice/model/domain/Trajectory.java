package com.stryde.webservice.model.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trajectory")
public class Trajectory {

    private static final String C_Id = "id";
    private static final String C_trainname = "trainname";
    private static final String C_trainnumber = "trainnumber";
    private static final String C_symbol = "symbol";
    private static final String C_product = "product";
    private static final String C_product_id = "product_id";
    private static final String C_mitteltype = "mitteltype";
    private static final String C_tripcode = "tripcode";
    private static final String C_destination_id = "destination_id";
    private static final String C_branch= "branch";
    private static final String C_line = "line";
    private static final String C_project = "project";
    private static final String C_network = "network";
    private static final String C_stateless = "stateless";
    private static final String C_operator = "operator";
    private static final String C_dateOfTrajectory = "trajectorydate";
    private static final String C_dateAdded = "dateadded";

    @Column(name= C_Id, nullable = false)@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //e.g R-Bahn RE1
    @Column(name = C_trainname)
    private String trainName;

    //e.g RE1
    @Column(name = C_trainnumber)
    private String trainNumber;

    //e.g RE1
    @Column(name = C_symbol)
    private String trainSymbol;

    //e.g R-Bahn
    @Column(name = C_product)
    private String product;

    @Column(name = C_product_id)
    private int productId;

    @Column(name = C_mitteltype)
    private int mittelType;

    @Column(name = C_tripcode)
    private String tripCode;

    //e.g 90
    @Column(name = C_branch)
    private String branch;

    @Column(name = C_line)
    private String line;

    //e.g DB AG
    @Column(name = C_operator)
    private String operator;

    @Column(name = C_network)
    private String network;

    @Column(name = C_project)
    private String project;

    @Column(name = C_stateless)
    private String stateless;

    @Column(name = C_destination_id)
    private String destinationId;

    @Column(name = C_dateOfTrajectory)
    private LocalDate trajectoryDate;

    @Column(name = C_dateAdded, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime dateAdded;

    @OneToMany(mappedBy = "trajectory", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Trip> trips = new ArrayList<>();

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}
