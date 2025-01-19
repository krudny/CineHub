package com.agh.cinehub_backend.service.StatisticsQuartz;

import com.agh.cinehub_backend.model.DailyTicketsStatistics;
import com.agh.cinehub_backend.model.Ticket;
import com.agh.cinehub_backend.model.modelUtils.DailyTicketsStatisticsId;
import com.agh.cinehub_backend.repository.DailyTicketsStatisticsRepository;
import com.agh.cinehub_backend.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DailyTotalTicketCounterJob implements Job {

    private final DailyTicketsStatisticsRepository statisticsRepository;
    private final TicketRepository ticketRepository;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        calculateTotalTicketsForToday();
    }

    public void calculateTotalTicketsForToday(){
        LocalDateTime startDay = LocalDate.now().atStartOfDay();
        LocalDateTime endDate = startDay.plusDays(1).toLocalDate().atStartOfDay();
        ticketTimeProcessor(startDay,endDate);
    }


    /** use the method carefully, it counts everything anew*/
    public void calculateTotalTicketsForLast14Days(){
        for(int day = 0; day<=14; day++) {
            LocalDateTime startDay = LocalDate.now().minusDays(day+1).atStartOfDay();
            LocalDateTime endDay = LocalDate.now().minusDays(day).atStartOfDay();
            ticketTimeProcessor(startDay, endDay);
        }
    }


    private void ticketTimeProcessor(LocalDateTime startDay, LocalDateTime endDate){
        List<Ticket> tickets = ticketRepository.findTicketsByDay(startDay,endDate);
        Map<Integer, Long> movieIdTotalTicketsToday = tickets.stream()
                .map(t -> t.getScreening().getMovie().getMovieId())
                .collect(Collectors.groupingBy(id -> id, Collectors.counting()));
        movieIdTotalTicketsToday.entrySet()
                .forEach(entry ->
                        buildAndSaveDayTicketStatistic(startDay, entry.getKey(), entry.getValue().intValue()));
    }

    private void buildAndSaveDayTicketStatistic(LocalDateTime startDay,
                                                int movieId,
                                                int totalSoldTickets
    ){
        DailyTicketsStatisticsId statisticsId =
                DailyTicketsStatisticsId
                .builder()
                .date(startDay.toLocalDate())
                .movieId(movieId).build();

        DailyTicketsStatistics dailyTicketsStatistics =
                DailyTicketsStatistics
                        .builder()
                        .id(statisticsId)
                        .totalDailyTickets(totalSoldTickets)
                        .build();

        statisticsRepository.save(dailyTicketsStatistics);
    }
}
