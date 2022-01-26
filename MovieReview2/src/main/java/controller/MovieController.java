package controller;

import domain.MovieResponseDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.MovieService;

/*영화 검색*/
@Controller
public class MovieController {
    private MovieService movieService;

    @RequestMapping(value="/movies/{keyword}", method = RequestMethod.GET)
    public MovieResponseDTO getMovie (@PathVariable String keyword){
        return movieService.findByKeyword(keyword);
    }

}
