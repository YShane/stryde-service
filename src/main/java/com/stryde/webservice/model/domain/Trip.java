package com.stryde.webservice.model.domain;

import com.stryde.webservice.dto.TravelRouting.StopDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "trip")
public class Trip {

    private static final String C_Id = "id";
    private static final String C_userid = "user_id";
    private static final String C_tripId= "trip_id";
    private static final String C_trajectoryid = "trajectory_id";
    private static final String C_line = "line";
    private static final String C_numberofchanges = "numberofchanges";
    private static final String C_starttime = "starttime";
    private static final String C_endtime = "endtime";
    private static final String C_datetimeadded = "datetimeadded";
    private static final String C_duration = "duration";

    @Column(name= C_Id, nullable = false)@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = C_tripId)
    private Long tripId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = C_userid, nullable = false)
    private User user = new User();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = C_trajectoryid, nullable = false)
    private Trajectory trajectory = new Trajectory();

    @Column(name = C_line)
    private String line = new String();

    @Column(name = C_numberofchanges)
    private int numberOfChanges;

    @Column(name = C_starttime)
    private LocalDateTime boardTime;

    @Column(name = C_endtime)
    private LocalDateTime alightTime;

    @Column(name = C_duration)
    private int duration;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Trajectory getTrajectory() {
        return trajectory;
    }

    public void setTrajectory(Trajectory trajectory) {
        this.trajectory = trajectory;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getNumberOfChanges() {
        return numberOfChanges;
    }

    public void setNumberOfChanges(int numberOfChanges) {
        this.numberOfChanges = numberOfChanges;
    }

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
}
