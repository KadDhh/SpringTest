package com.example.springtest.logic;

import com.example.springtest.domain.GameStats;
import com.example.springtest.domain.User;
import com.example.springtest.repos.GameStatsRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


public class GameLogic {
    private static GameStats gameStats = new GameStats();


    public GameLogic() {
    }

    private int getBuilding1Price(GameStatsRepo loadRepo) {
        loadBuildingOneFromSQL(1, loadRepo);
        int building1Price = ((gameStats.getBuildingOne() + 1) * (3 + gameStats.getBuildingOne()) * 2);
        return building1Price;
    }
    private int getBuilding2Price(GameStatsRepo loadRepo) {
        loadBuildingTwoFromSQL(1, loadRepo);
        int building2Price = ((gameStats.getBuildingTwo() + 1) * (22 + gameStats.getBuildingTwo()) * 2);
        return building2Price;
    }



    private int getPoints() {
        return gameStats.getPoints();
    }

    private int getBuildingOne() {
        if (gameStats.getBuildingOne()>0)
            return gameStats.getBuildingOne();
        else return 0;
    }
    private int getBuildingTwo() {
        if (gameStats.getBuildingTwo()>0)
            return gameStats.getBuildingTwo();
        else return 0;
    }

    public void addPointsPerClick(Map model) {
        gameStats.setPoints(gameStats.getPoints() + 1);
        if (gameStats.getBuildingOne() > 0) {
            gameStats.setPoints(gameStats.getPoints() + gameStats.getBuildingOne());
        }
        if (gameStats.getBuildingTwo() > 0) {
            gameStats.setPoints(gameStats.getPoints() + gameStats.getBuildingTwo() * 8);
        }
        int i = ThreadLocalRandom.current().nextInt(0, 2);
        if (i == 0){
            model.put("info", "клик!"); }
        if (i == 1) {
            model.put("info", "клак!"); }
    }

    public void buyBuildingOne(Map model, GameStatsRepo loadRepo, int amountOfBuying) {  // When buying 10 at once, price is lower (coz lvl 1 building * 10 != lvl 1+2+3...+10 buildings price)
        if (getPoints()<(getBuilding1Price(loadRepo)*amountOfBuying)){
            model.put("info", "недостаточно денег");
        }
        else {
            gameStats.setPoints(gameStats.getPoints() - (getBuilding1Price(loadRepo)*amountOfBuying));
            gameStats.setBuildingOne(gameStats.getBuildingOne() + amountOfBuying);
            model.put("info", "зданиеОдин x" + amountOfBuying + " куплено!");
        }
    }

    public void buyBuildingTwo(Map model, GameStatsRepo loadRepo, int amountOfBuying) { // same here
        if (getPoints()<(getBuilding2Price(loadRepo)*amountOfBuying)){
            model.put("info", "недостаточно денег");
        }
        else {
            gameStats.setPoints(gameStats.getPoints() - (getBuilding2Price(loadRepo)*amountOfBuying));
            gameStats.setBuildingTwo(gameStats.getBuildingTwo() + amountOfBuying);
            model.put("info", "зданиеДва x" + amountOfBuying + " куплено!");
        }
    }

    public int getIncome() {
        return 1 + gameStats.getBuildingOne() + gameStats.getBuildingTwo() * 8;
    }


    // USER info \\

    public String getUserName(User userToGetNameFrom) {
        if (userToGetNameFrom == null) return "Неавторизованный пользователь";
        else return userToGetNameFrom.getUsername();
    }


    // Screen Info \\

    Map<Object, String> ultimateModel = new HashMap<>();

    public void loadAndShowInfo(Map model){
        model.putAll(ultimateModel);
    }

    public void saveInfo(Map model, GameStatsRepo loadRepo) {
        model.put("buildingOne", getBuildingOne());
        model.put("buildingTwo", getBuildingTwo());
        model.put("pointsShow", getPoints());
        model.put("income", getIncome());
        model.put("buildingOnePrice", getBuilding1Price(loadRepo));
        model.put("buildingTwoPrice", getBuilding2Price(loadRepo));
        ultimateModel.putAll(model);
    }

    private int i = 0;
    public void saveUserNameInfo(Map model, User userToGetNameFrom){
        if (i==0) {
            model.put("lastUser", getUserName(userToGetNameFrom));
            i ++;
            ultimateModel.putAll(model);
            return;
        }
        if (i==1) {
            model.put("lastUser2", getUserName(userToGetNameFrom));
            i = 0;
            ultimateModel.putAll(model);
        }
    }

    // SQL \\

    private int loadBuildingOneFromSQL(long id, GameStatsRepo loadRepo) {
        if (loadRepo.findById(id).isPresent()) {
            GameStats gameStats1 = loadRepo.findById(id).orElse(new GameStats());
            return gameStats1.getBuildingOne();
        } else return 0;
    }
    private int loadBuildingTwoFromSQL(long id, GameStatsRepo loadRepo) {
        if (loadRepo.findById(id).isPresent()) {
            GameStats gameStats1 = loadRepo.findById(id).orElse(new GameStats());
            return gameStats1.getBuildingTwo();
        } else return 0;
    }


    public void save(GameStatsRepo saveRepo) {
        gameStats.setId((long) 1);
        saveRepo.save(gameStats);
    }

    // GET SET from BD \\

    public void loadFromSQL(GameStatsRepo loadRepo) {
        GameStats gameStats1 = loadRepo.findById((long)1).orElse(new GameStats());
        gameStats.setPoints(gameStats1.getPoints());
        gameStats.setBuildingOne(gameStats1.getBuildingOne());
        gameStats.setBuildingTwo(gameStats1.getBuildingTwo());
    }

    public Long getId() {
        return gameStats.getId();
    }
    public void setId(Long id) {
        gameStats.setId(id);
    }

}