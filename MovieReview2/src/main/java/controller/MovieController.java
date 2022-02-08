package controller;

import domain.MovieResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.MovieService;


@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(value="/movies/{keyword}", method = RequestMethod.GET)
    public ResponseEntity getMovie(@PathVariable String keyword){
        return new ResponseEntity(movieService.findByKeyword(keyword), HttpStatus.OK);
        //body로 json형식 movieResponseDto 들어갔을 것
    }
}
