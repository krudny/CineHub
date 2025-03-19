package com.agh.cinehub_backend.model;

import com.agh.cinehub_backend.model.modelUtils.DailyTicketsStatisticsId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "daily_tickets_statistics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyTicketsStatistics {

    @EmbeddedId
    private DailyTicketsStatisticsId id;

    @Column(name = "total_daily_tickets", nullable = false)
    private int totalDailyTickets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyTicketsStatistics that = (DailyTicketsStatistics) o;
        return totalDailyTickets == that.totalDailyTickets &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalDailyTickets);
    }

    @Override
    public String toString() {
        return "DailyTicketsStatistics{" +
                "id=" + id +
                ", totalDailyTickets=" + totalDailyTickets +
                '}';
    }
}
