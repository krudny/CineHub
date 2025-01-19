package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.modelUtils.DailyTicketsStatisticsId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyTicketsStatisticsRepository
        extends JpaRepository<com.agh.cinehub_backend.model.DailyTicketsStatistics, DailyTicketsStatisticsId> {


    List<DailyTicketsStatisticsRepository> findByIdMovieId(int movieId);

    List<DailyTicketsStatisticsRepository> findByIdMovieIdAndIdDateBetween(int movieId, LocalDate startDate, LocalDate endDate);

    List<DailyTicketsStatisticsRepository> findByIdDate(LocalDate date);

    DailyTicketsStatisticsRepository findByIdMovieIdAndIdDate(int movieId, LocalDate date);
}
