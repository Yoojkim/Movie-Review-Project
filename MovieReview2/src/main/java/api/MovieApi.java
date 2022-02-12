package api;

import domain.MovieResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieApi {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.client_id}")
    private String CLIENT_ID;

    @Value("${api.client_secret}")
    private String CLIENT_SECRET;

    private final String queryUrl = "https://openapi.naver.com/v1/search/movie.json?query={keyword}";

    public MovieResponse requestMovie(String keyword) throws Exception {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);
        final HttpEntity<String> entity=new HttpEntity<>(headers);

        ResponseEntity<MovieResponse> response=restTemplate.exchange(queryUrl, HttpMethod.GET, entity, MovieResponse.class,keyword);
        return response.getBody();
    }

}
