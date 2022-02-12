package domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;

//MovieDTO 만들어서 userRating 제거하면 좋을 듯
@Data
public class Movie {
    @JsonProperty(value="title")
    @ApiParam(value = "영화 제목", required = true)
    private String title;

    @JsonProperty(value="link")
    @ApiParam(value = "영화 하이퍼링크(네이버)", required = true)
    private String link;

    @JsonProperty(value="image")
    @ApiParam(value = "영화 img", required = true)
    private String image;

    @JsonProperty(value="subtitle")
    @ApiParam(value = "영화 부제", required = true)
    private String subtitle;

    @JsonProperty(value="pubDate")
    @ApiParam(value = "영화 개봉년도", required = true)
    private String pubDate;

    @JsonProperty(value="director")
    @ApiParam(value = "감독", required = true)
    private String director;

    @JsonProperty(value="actor")
    @ApiParam(value = "배우", required = true)
    private String actor;

    @ApiParam(value = "영화 평점(네이버 영화)", required = false)
    @ApiModelProperty(hidden = true)
    private float userRating;

}
