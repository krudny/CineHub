package com.agh.cinehub_backend.service.StatisticsQuartz;

import com.agh.cinehub_backend.model.MovieRating;
import com.agh.cinehub_backend.repository.MovieRatingsRepository;
import com.agh.cinehub_backend.repository.MovieRepository;
import com.agh.cinehub_backend.repository.ReviewRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MovieRatingsJob implements Job {

    private final ReviewRepository reviewRepository;
    private final MovieRatingsRepository movieRatingRepository;
    private final MovieRepository movieRepository;

    private Map<Integer, List<Integer>> movieReviewMap;

    public MovieRatingsJob(MovieRatingsRepository movieRatingRepository,
                           ReviewRepository reviewRepository,
                           MovieRepository movieRepository) {
        this.movieRatingRepository = movieRatingRepository;
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext){
        updateAllRatings();
    }


    public void updateAllRatings(){

        updateReviewMap();

        Map<Integer,Double> moveIdMedianMap = getMedianMap();
        Map<Integer, Double> movieIdAvgMap = getAvgMap();

        movieReviewMap.entrySet().stream().forEach(entry -> {
            int movieId = entry.getKey();
            updateRating(
                    movieId,
                    moveIdMedianMap.get(movieId),
                    highestMovieScore(movieId),
                    lowestMovieScore(movieId),
                    movieIdAvgMap.get(movieId),
                    totalMovieRatings(movieId)
            );
        });


    }

    private void updateRating(int movieId,
                              double medianScore,
                              double highestScore,
                              double lowestScore,
                              double avgScore,
                              int totalRatings){

        movieRatingRepository.getMovieRatingByMovieId(movieId)
                .ifPresentOrElse(movieRating-> {
                    movieRating.setAverageRating(avgScore);
                    movieRating.setLowestRating(lowestScore);
                    movieRating.setHighestRating(highestScore);
                    movieRating.setMedianRating(medianScore);
                    movieRating.setTotalRatings(totalRatings);

                    movieRatingRepository.save(movieRating);
                }, () -> {
                    MovieRating movieRating = MovieRating.builder().movie(movieRepository.findByMovieId(movieId).get())
                            .averageRating(avgScore)
                            .lowestRating(lowestScore)
                            .highestRating(highestScore)
                            .totalRatings(totalRatings)
                            .medianRating(medianScore)
                            .build();

                    movieRatingRepository.save(movieRating);
                });

    }


    private void updateReviewMap(){
        Map<Integer, List<Integer>> potentialReviewMap = movieRepository.findAll()
                .stream()
                .collect(Collectors.toMap(r -> r.getMovieId(), r -> new ArrayList<>()));

        reviewRepository.findAll().stream().forEach(r -> {
            potentialReviewMap.computeIfPresent(r.getMovie().getMovieId(), (key, list) -> {
                list.add(r.getScore());
                return list;
            });
        });
        this.movieReviewMap = potentialReviewMap;
    }


    private Map<Integer, Double> getMedianMap(){
        Map<Integer, Double> output = new HashMap<>();

        for(Map.Entry<Integer, List<Integer>> entry : this.movieReviewMap.entrySet()){
            List<Integer> medianList = entry.getValue();
            Collections.sort(medianList);

            int size = medianList.size();
            Double medianValue;
            if (size % 2 == 0) {
                int mid = size / 2;
                medianValue = (double) ((medianList.get(mid) + medianList.get(mid-1)) / 2);
            }else {
                medianValue = Double.valueOf(medianList.get(size / 2));
            }
            output.put(entry.getKey(), medianValue);
        }
        return output;
    }

    private Map<Integer, Double> getAvgMap(){
        Map<Integer, Double> output = new HashMap<>();

        this.movieReviewMap.entrySet().stream().forEach(e -> {
            output.put(e.getKey(), e.getValue().stream().mapToDouble(score -> score).average().getAsDouble());
        });
        return output;
    }

    private int highestMovieScore(int movieId) {
        return this.movieReviewMap.get(movieId).stream().max(Comparator.naturalOrder()).orElse(0);
    }

    private int lowestMovieScore(int movieId) {
        return this.movieReviewMap.get(movieId).stream().min(Comparator.naturalOrder()).orElse(0);
    }

    private int totalMovieRatings(int movieId){
        return this.movieReviewMap.get(movieId).size();
    }

}
