package domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

//createReview에서 reqestbody 사용하기 위함
//MovieDTO(Object) + review(String) Wrapper class

@Getter
@Setter
public class MovieWrapper {
    private Movie movie;
    private String review;
}
