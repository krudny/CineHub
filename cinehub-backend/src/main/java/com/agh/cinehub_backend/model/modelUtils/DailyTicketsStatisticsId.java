package com.agh.cinehub_backend.model.modelUtils;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DailyTicketsStatisticsId implements Serializable {

    @Column(name = "movie_id", nullable = false)
    private int movieId;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyTicketsStatisticsId that = (DailyTicketsStatisticsId) o;
        return movieId == that.movieId &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, date);
    }

    @Override
    public String toString() {
        return "DailyTicketsStatisticsId{" +
                "movieId=" + movieId +
                ", date=" + date +
                '}';
    }

}
