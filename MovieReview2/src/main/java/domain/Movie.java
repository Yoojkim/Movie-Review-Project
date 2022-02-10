package domain;

import io.swagger.annotations.ApiParam;
import lombok.Data;

@Data
public class Movie {
    @ApiParam(value = "영화 제목", required = true)
    private String title;

    @ApiParam(value = "영화 하이퍼링크(네이버)", required = true)
    private String link;

    @ApiParam(value = "영화 img", required = true)
    private String image;

    @ApiParam(value = "영화 부제", required = true)
    private String subtitle;

    @ApiParam(value = "영화 개봉년도", required = true)
    private String pubDate;

    @ApiParam(value = "감독", required = true)
    private String director;

    @ApiParam(value = "배우", required = true)
    private String actor;

    @ApiParam(value = "영화 평점(네이버 영화)", required = false)
    private float userRating;

}
