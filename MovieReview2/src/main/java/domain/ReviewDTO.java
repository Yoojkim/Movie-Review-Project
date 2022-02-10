package domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewDTO {
    private Long rid;
    private Long mid;
    private Long uid;
    private String nickName;
    private String review;
    private int likes;
}
