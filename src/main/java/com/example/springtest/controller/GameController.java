package com.example.springtest.controller;

import com.example.springtest.Logic.GameLogic;
import com.example.springtest.repos.GameStatsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Controller
@RequestMapping("/gamePage")
public class GameController {
    private static GameLogic gameLogic = new GameLogic();

    @Autowired
    private GameStatsRepo gameStatsRepo;

    @GetMapping
    public String gamePage(Map<String, Object> model) {
        gameLogic.loadFromSQL(gameStatsRepo);
        gameLogic.saveInfo(model, gameStatsRepo);
        gameLogic.loadAndShowInfo(model);
        return "gamePage";
    }


    @PostMapping(params = "buttonClick")
    public String buttonClick(Map<String, Object> model) {
        gameLogic.addPointsPerClick(model);
        gameLogic.save(gameStatsRepo);
        gameLogic.saveInfo(model, gameStatsRepo);
        return "redirect:/gamePage";
    }

    @PostMapping(params = "buyBuildOne")
    public String buyBuildOne(Map<String, Object> model) {
        gameLogic.buyBuildingOne(model, gameStatsRepo);
        gameLogic.save(gameStatsRepo);
        gameLogic.saveInfo(model, gameStatsRepo);
        return "redirect:/gamePage";
    }

    @PostMapping(params = "buyBuildTwo")
    public String buyBuildTwo(Map<String, Object> model) {
        gameLogic.buyBuildingTwo(model, gameStatsRepo);
        gameLogic.save(gameStatsRepo);
        gameLogic.saveInfo(model, gameStatsRepo);
        return "redirect:/gamePage";
    }

}
