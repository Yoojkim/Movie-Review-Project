package service;

import domain.MovieResponseDTO;

public interface MovieService {
    MovieResponseDTO findByKeyword(String keyword);
}
