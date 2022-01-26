package domain;

import java.util.Date;

public class MovieDTO {
    /*API에서 넘어오는 정보 확인 후 작성*/
    private String title;
    private String link;
    private String image;
    private String subtitle;
    private Date pubDate;
    private String director;
    private String actor;
    private float userRating; /*원 타입은 integer*/
}
