package com.agh.cinehub_backend.service.StatisticsQuartz;

import com.agh.cinehub_backend.model.Movie;
import com.agh.cinehub_backend.model.Ticket;
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
import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DailyTotalTicketCounterJob implements Job {

    private final DailyTicketsStatisticsRepository statisticsRepository;
    private final TicketRepository ticketRepository;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
//        getTicketsByDate();
    }

    private void calculateTotalTicketsForToday(){
        LocalDateTime startDay = LocalDate.now().atStartOfDay();
        LocalDateTime endDate = startDay.plusDays(1).toLocalDate().atStartOfDay();
        List<Ticket> tickets = getTicketsByDate(startDay,endDate);



    }


    /** use the method carefully, it counts everything anew*/
    public void calculateTotalTicketsForLast14Days(){
        LocalDateTime startDay = LocalDate.now().minusDays(14).atStartOfDay();
        LocalDateTime endDay = LocalDate.now().atStartOfDay();
        List<Ticket> tickets = getTicketsByDate(startDay,endDay);
        Map<Integer, Long> movieIdTotalTicketsToday = tickets.stream()
               .map(t -> t.getScreening().getMovie().getMovieId())
               .collect(Collectors.groupingBy(id -> id, Collectors.counting()));



    }


    private List<Ticket> getTicketsByDate(LocalDateTime startDay, LocalDateTime endDate){
        List<Ticket> tickets = ticketRepository.findTicketsByDay(startDay, endDate);
        return tickets;
    }
}
