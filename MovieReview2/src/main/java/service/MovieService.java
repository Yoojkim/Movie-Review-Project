package service;

import domain.MovieResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    public MovieResponseDTO findByKeyword(String keyword);
}
