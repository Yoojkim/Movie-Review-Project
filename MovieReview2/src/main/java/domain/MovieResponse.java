package domain;

import lombok.Data;

import java.util.Date;

@Data
public class MovieResponse {
    private Date lastBuildDate;
    private int total;
    private int start;
    private int display;
    private Movie[] items;
}
