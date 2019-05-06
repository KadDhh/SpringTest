package com.example.springtest.controller;

import com.example.springtest.domain.GameStats;
import com.example.springtest.repos.GameStatsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


@Controller
@RequestMapping("/gamePage")
public class GameController {
    GameStats gameStats = new GameStats();

    @Autowired
    private GameStatsRepo gameStatsRepo;

    @GetMapping
    public String gamePage(Map<String, Object> model) {
        gameStats.setPoints(loadPointsFromSQL(1));
        gameStats.setBuildingOne(loadBuildingOneFromSQL(1));
        showInfo(model);
        return "gamePage";
    }


    @PostMapping(params = "buttonClick")
    public String buttonClick(Map<String, Object> model) {
        addPointsPerClick();
        showInfo(model);
        int i = ThreadLocalRandom.current().nextInt(0, 2);
        if (i == 0){
            model.put("message", "клик!"); }
        if (i == 1) {
            model.put("message", "клак!"); }

        return "gamePage";
    }

    @PostMapping(params = "buyBuildOne")
    public String buyBuildOne(Map<String, Object> model) {
        if (getPoints()<getBuilding1Price()){
            model.put("message", "недостаточно денег");
        }
        else {
            buyBuildingOne();
            model.put("message", "зданиеОдин куплено!");
        }
        showInfo(model);
        return "gamePage";

    }


                // --- LOGIC -- \\

    int building1Price = (gameStats.getBuildingOne() + 1) * (5 + gameStats.getBuildingOne());

    public int getBuilding1Price() {
        return building1Price;
    }
    public int getPoints() {
        return gameStats.getPoints();
    }
    private int getBuildingOne() {
        return gameStats.getBuildingOne();
    }

    public void addPointsPerClick() {
        gameStats.setPoints(gameStats.getPoints()+1);
        if (gameStats.getBuildingOne()>0){
            gameStats.setPoints(gameStats.getPoints()+gameStats.getBuildingOne());
        }
        save();
    }

    public void buyBuildingOne(){
        gameStats.setPoints(gameStats.getPoints() - building1Price);
        gameStats.setBuildingOne(gameStats.getBuildingOne()+1);
    }

    public void showInfo(Map model) { // перенести в логику
        model.put("buildingOne", getBuildingOne());
        model.put("pointsShow", getPoints());
        model.put("buildingOnePrice", building1Price);
        model.put("message", "test");
    }

    public int loadPointsFromSQL(long id){
        if (gameStatsRepo.findById(id).isPresent()) {
            GameStats gameStats1 = gameStatsRepo.findById(id).orElse(new GameStats());
            return gameStats1.getPoints();
        }
        else return 0;
    }
    public int loadBuildingOneFromSQL(long id){
        if (gameStatsRepo.findById(id).isPresent()) {
            GameStats gameStats1 = gameStatsRepo.findById(id).orElse(new GameStats());
            return gameStats1.getBuildingOne();
        }
        else return 0;
    }

    public void save() {
        gameStats.setId((long)1);
        gameStatsRepo.save(gameStats);
    }

}
