package com.example.springtest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GameStats {

    @Id
    private Long id;

    @Column(name = "Pts")
    private int points;
    @Column(name = "Build1")
    private int buildingOne;
    @Column(name = "Build2")
    private int buildingTwo;


    public GameStats() {
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getBuildingOne() {
        return buildingOne;
    }

    public int getBuildingTwo() {
        return buildingTwo;
    }

    public void setBuildingOne(int buildingOne) {
        this.buildingOne = buildingOne;
    }

    public void setBuildingTwo(int buildingTwo) {
        this.buildingTwo = buildingTwo;
    }
    public Long getId() {return id;}
    public void setId(Long id) {
        this.id = id;
    }
}
