package serviceImpl;

import api.MovieApi;
import domain.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.MovieService;


@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieApi movieApi;

    @Transactional(readOnly = true)
    public MovieResponse findByKeyword(String keyword) throws Exception {
        return movieApi.requestMovie(keyword);
    }
}
