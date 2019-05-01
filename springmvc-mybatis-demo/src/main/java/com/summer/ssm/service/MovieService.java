package com.summer.ssm.service;

import com.summer.ssm.dao.MovieMapper;
import com.summer.ssm.pojo.Movie;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MovieService {
    @Resource
    private MovieMapper movieMapper;

    public int insert(Movie movie) {
        return movieMapper.insert(movie);
    }

    public int delete(int id) {
        return movieMapper.delete(id);
    }

    public int update(Movie movie) {
        return movieMapper.update(movie);
    }

    public List<Movie> list() {
        return movieMapper.list();
    }
}
