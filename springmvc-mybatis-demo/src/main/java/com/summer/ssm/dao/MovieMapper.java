package com.summer.ssm.dao;

import com.summer.ssm.pojo.Movie;

import java.util.List;

public interface MovieMapper {
    int insert(Movie movie);

    int delete(int id);

    int update(Movie movie);

    List<Movie> list();
}
