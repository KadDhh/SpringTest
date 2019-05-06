package com.example.springtest.repos;

import com.example.springtest.domain.GameStats;
import org.springframework.data.repository.CrudRepository;

public interface GameStatsRepo extends CrudRepository<GameStats, Long> {
}
