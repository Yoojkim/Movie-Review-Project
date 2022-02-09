package service;

import domain.MovieResponse;

public interface MovieService {
    MovieResponse findByKeyword(String keyword) throws Exception;
}
