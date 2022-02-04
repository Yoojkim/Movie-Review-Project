package service;

import domain.MovieResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface MovieService {
    MovieResponseDTO findByKeyword(String keyword);
}
