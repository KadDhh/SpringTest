package com.example.springtest.Logic;

import com.example.springtest.domain.GameStats;
import com.example.springtest.repos.GameStatsRepo;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class GameLogic {
    private static GameStats gameStats = new GameStats();


    public GameLogic() {
    }

    // --- LOGIC -- \\

    private int getBuilding1Price(GameStatsRepo loadRepo) {
        loadBuildingOneFromSQL(1, loadRepo);
        int building1Price = (gameStats.getBuildingOne() + 1) * (5 + gameStats.getBuildingOne());
        return building1Price;
    }

    private int getPoints() {
        return gameStats.getPoints();
    }

    private int getBuildingOne() {
        return gameStats.getBuildingOne();
    }

    public void addPointsPerClick(Map model) {
        gameStats.setPoints(gameStats.getPoints() + 1);
        if (gameStats.getBuildingOne() > 0) {
            gameStats.setPoints(gameStats.getPoints() + gameStats.getBuildingOne());
        }
        int i = ThreadLocalRandom.current().nextInt(0, 2);
        if (i == 0){
            model.put("message", "клик!"); }
        if (i == 1) {
            model.put("message", "клак!"); }
    }

    public void buyBuildingOne(Map model, GameStatsRepo loadRepo) {
        if (getPoints()<getBuilding1Price(loadRepo)){
            model.put("message", "недостаточно денег");
        }
        else {
            gameStats.setPoints(gameStats.getPoints() - getBuilding1Price(loadRepo));
            gameStats.setBuildingOne(gameStats.getBuildingOne() + 1);
            model.put("message", "зданиеОдин куплено!");
        }
    }

    public void showInfo(Map model, GameStatsRepo loadRepo) {
        model.put("buildingOne", getBuildingOne());
        model.put("pointsShow", getPoints());
        model.put("buildingOnePrice", getBuilding1Price(loadRepo));
    }

    private int loadPointsFromSQL(long id, GameStatsRepo loadRepo) {
        if (loadRepo.findById(id).isPresent()) {
            GameStats gameStats1 = loadRepo.findById(id).orElse(new GameStats());
            return gameStats1.getPoints();
        } else return 0;
    }

    private int loadBuildingOneFromSQL(long id, GameStatsRepo loadRepo) {
        if (loadRepo.findById(id).isPresent()) {
            GameStats gameStats1 = loadRepo.findById(id).orElse(new GameStats());
            return gameStats1.getBuildingOne();
        } else return 0;
    }

    public void save(GameStatsRepo saveRepo) {
        gameStats.setId((long) 1);
        saveRepo.save(gameStats);
    }

    // GET SET from BD \\

    public void loadFromSQL(GameStatsRepo loadRepo) {
        gameStats.setPoints(loadPointsFromSQL(1, loadRepo));
        gameStats.setBuildingOne(loadBuildingOneFromSQL(1,loadRepo));
    }

    private int getPointsFromBD() {
        return gameStats.getPoints();
    }

    private void setPointsToBD(int points) {
        gameStats.setPoints(points);
    }

    private int getBuildingOneFromBD() {
        return gameStats.getBuildingOne();
    }

    private void setBuildingOne(int buildingOne) {
        gameStats.setBuildingOne(buildingOne);
    }

    public Long getId() {
        return gameStats.getId();
    }
    public void setId(Long id) {
        gameStats.setId(id);
    }

}