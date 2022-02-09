package domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//mid 추가, userRating 삭제 (Movie에서)
@Getter
@Setter
public class MovieEntity {
    private Long mid;
    private String title;
    private String link;
    private String image;
    private String subtitle;
    private Date pubDate;
    private String director;
    private String actor;
}
