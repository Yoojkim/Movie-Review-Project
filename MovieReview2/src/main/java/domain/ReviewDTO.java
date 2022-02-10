package domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Setter
@Getter
public class ReviewDTO {
    private Long rid;
    private Long mid;
    private Long uid;
    private String nickName;
    private String review;
    private int likes;
    private ArrayList<ArrayList<CommentDTO>> comments;

    //시간 나타내기
    private Date created_at;
}
