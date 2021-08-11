package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDatailsDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.dto.MovieReviewsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private MovieRepository repository;

    @Autowired
    private GenreRepository genreRepository;
    
    @Transactional(readOnly = true)
    public MovieDatailsDTO findMovieById(Long id) {
    	Optional<Movie> obj = repository.findById(id);
    	Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));    	
    	return new MovieDatailsDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<MovieReviewsDTO> findMovieByIdWithReviews(Long id) {
        Optional<Movie> obj = repository.findById(id);
        List<MovieReviewsDTO> list = new ArrayList<>();
        obj.get().getReviews().forEach(x -> list.add(new MovieReviewsDTO(x)));
        return list;
    }

    @Transactional(readOnly = true)
    public Page<MovieGenreDTO> findAll(Long genreId, Pageable pageable){
        genreId = (genreId == 0L) ? null : genreId;
        Page<Movie> list = repository.findByGenrePaged(genreId, pageable);
        return list.map(movie -> new MovieGenreDTO(movie));
    }

}
