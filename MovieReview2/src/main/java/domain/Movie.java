package domain;

import lombok.Data;

import java.util.Date;

@Data
public class Movie {
    private String title;
    private String link;
    private String image;
    private String subtitle;
    private String pubDate;
    private String director;
    private String actor;
    private float userRating;

}
