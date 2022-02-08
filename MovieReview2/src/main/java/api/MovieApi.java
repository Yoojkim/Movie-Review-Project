package api;

import domain.MovieResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


//jwt token 있어야 수행
@Service
public class MovieApi {
    //api 다시 짜기
    private final RestTemplate restTemplate=new RestTemplate();
    private final String CLIENT_ID="zjg2mq8O1qPGx5sOXfvz";
    private final String CLIENT_SECRET="DGHfARaKRX";
    private String queryUrl = "https://openapi.naver.com/v1/search/movie.json?query=";

    public MovieResponseDTO requestMovie(String keyword){
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);
        queryUrl+=keyword;

        final HttpEntity<String> entity=new HttpEntity<>(headers);

        return restTemplate.exchange(queryUrl, HttpMethod.GET,entity,MovieResponseDTO.class).getBody();
    }

}
