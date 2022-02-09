package controller;

import domain.MovieResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.MovieService;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @ResponseBody
    @RequestMapping(value="/movies/{keyword}", method = RequestMethod.GET)
    public MovieResponseDTO getMovie(@PathVariable String keyword) throws Exception {
        return movieService.findByKeyword(keyword);
    }
}
