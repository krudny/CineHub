package com.agh.cinehub_backend.repository;

import com.agh.cinehub_backend.model.DailyTicketsStatistics;
import com.agh.cinehub_backend.model.modelUtils.DailyTicketsStatisticsId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface DailyTicketsStatisticsRepository
        extends JpaRepository<com.agh.cinehub_backend.model.DailyTicketsStatistics, DailyTicketsStatisticsId> {


    List<DailyTicketsStatistics> findByIdMovieId(int movieId);

    List<DailyTicketsStatistics> findByIdMovieIdAndIdDateBetween(int movieId, LocalDate startDate, LocalDate endDate);

    List<DailyTicketsStatistics> findByIdDate(LocalDate date);

    DailyTicketsStatistics findByIdMovieIdAndIdDate(int movieId, LocalDate date);
}
