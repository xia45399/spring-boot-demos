package com.summer.ssm.controller;

import com.summer.ssm.pojo.Movie;
import com.summer.ssm.service.MovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("controller/movie")
public class MovieController {

    @Resource
    private MovieService movieService;

    @RequestMapping("insert")
    public Object insert(Movie movie) {
        movie.setAddTime(System.currentTimeMillis() / 1000);
        return movieService.insert(movie);
    }

    @RequestMapping("delete")
    public Object delete(int id) {
        return movieService.delete(id);
    }

    @RequestMapping("update")
    public Object update(Movie movie) {
        return movieService.update(movie);
    }

    @RequestMapping("list")
    public Object list() {
        return movieService.list();
    }
}
