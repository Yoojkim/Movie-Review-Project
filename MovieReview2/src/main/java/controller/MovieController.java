package controller;

import domain.MovieResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @ApiOperation(value="영화 정보 가져오는 api",notes="쿼리의 keyword로 여러 영화 객체를 반환한다.")
    @RequestMapping(value="/movies/{keyword}", method = RequestMethod.GET)
    public MovieResponse getMovie(@PathVariable String keyword) throws Exception {
        return movieService.findByKeyword(keyword);
    }
}