package io.javabrains.movie_catalog_service.resources;

import io.javabrains.movie_catalog_service.models.CatalogItems;
import io.javabrains.movie_catalog_service.models.Movie;
import io.javabrains.movie_catalog_service.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/catalog")
public class MovieCatelogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userID}")
    public List<CatalogItems> getCatalogs(@PathVariable("userID") String userID) {

        // get all rated movie ids
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );


        // for each movie id call movie info service and get details

        return ratings.stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/1", Movie.class);
            return new CatalogItems(movie.getName(), "it is a south indian movie", rating.getRating());
        }).collect(Collectors.toList());

        // put them all together

    }
}
