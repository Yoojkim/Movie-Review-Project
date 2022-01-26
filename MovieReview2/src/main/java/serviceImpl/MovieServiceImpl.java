package serviceImpl;

import api.MovieApi;
import domain.MovieResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieApi movieApi;

    @Transactional(readOnly = true)
    public MovieResponseDTO findByKeyword(String keyword){
        return movieApi.requestMovie(keyword);
    }
}
