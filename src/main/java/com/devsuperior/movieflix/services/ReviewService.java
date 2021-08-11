package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;

@Service
public class ReviewService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public ReviewDTO insertReview(ReviewDTO dto) {

        User user = authService.authenticated();

        Optional<Movie> movie = movieRepository.findById(dto.getMovieId());
        Review review = new Review();
        review.setText(dto.getText());
        review.setMovie(movie.get());
        review.setUser(user);
        reviewRepository.save(review);
        dto.setId(review.getId());
        dto.setUser(new UserDTO(user));
        return dto;
    }
}
