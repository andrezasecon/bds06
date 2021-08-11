package com.devsuperior.movieflix.resources;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.services.GenreService;

@RestController
@RequestMapping(value = "/genres")
public class GenreResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private GenreService service;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> findAllDepartments() {
        List<GenreDTO> list = service.findAllGenres();
        return ResponseEntity.ok().body(list);

    }

}
