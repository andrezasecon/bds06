package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.dto.MovieDatailsDTO;
import com.devsuperior.movieflix.dto.MovieGenreDTO;
import com.devsuperior.movieflix.dto.MovieReviewsDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private MovieService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDatailsDTO> findById(@PathVariable Long id) {
        MovieDatailsDTO movie = service.findMovieById(id);
        return ResponseEntity.ok().body(movie);

    }
    
    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<MovieReviewsDTO>> findByIdWithReviews(@PathVariable Long id) {
        List<MovieReviewsDTO> movies = service.findMovieByIdWithReviews(id);
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping
    public ResponseEntity<Page<MovieGenreDTO>> findAll(
            @RequestParam(name = "genreId", defaultValue = "0") Long genreId,
            @PageableDefault(sort = {"title"}, direction = Sort.Direction.ASC) Pageable pageable    ){
        Page<MovieGenreDTO> movies = service.findAll(genreId, pageable);
        return ResponseEntity.ok().body(movies);
    }
}
