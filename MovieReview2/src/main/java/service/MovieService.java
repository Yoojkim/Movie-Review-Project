package service;

import domain.MovieResponseDTO;

import java.util.List;

public interface MovieService {
    MovieResponseDTO findByKeyword(String keyword) throws Exception;
}
