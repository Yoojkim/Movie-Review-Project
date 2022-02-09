package mapper;

import domain.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieMapper {
    Long registerMovie(Movie movie);
    Long getMid(Movie movie);
    boolean linkExist(String link);
}
