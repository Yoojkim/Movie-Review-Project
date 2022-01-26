package api;

import domain.MovieResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MovieApi {
    private final RestTemplate restTemplate=new RestTemplate();
    private final String CLIENT_ID="";
    private final String CLIENT_SECRET="";
    private final String queryUrl = "https://openapi.naver.com/v1/search/movie.json?query={keyword}";

    public MovieResponseDTO requestMovie(String keyword){
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);

        final HttpEntity<String> entity=new HttpEntity<>(headers);

        return restTemplate.exchange(queryUrl, HttpMethod.GET,entity,MovieResponseDTO.class,keyword).getBody();
    }

}
