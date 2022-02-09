package api;

import domain.MovieResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//jwt token 있어야 수행

@Service
public class MovieApi {

    @Autowired
    private RestTemplate restTemplate;

    private final String CLIENT_ID="zjg2mq8O1qPGx5sOXfvz";
    private final String CLIENT_SECRET="DGHfARaKRX";
    private final String queryUrl = "https://openapi.naver.com/v1/search/movie.json?query={keyword}";

    public MovieResponseDTO requestMovie(String keyword) throws Exception {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", CLIENT_ID);
        headers.set("X-Naver-Client-Secret", CLIENT_SECRET);

        final HttpEntity<String> entity=new HttpEntity<>(headers);

        try{
            ResponseEntity<MovieResponseDTO> response=restTemplate.exchange(queryUrl, HttpMethod.GET, entity, MovieResponseDTO.class,keyword);
            return response.getBody();
        }catch(Exception e){
            throw new Exception("resttemplate exchange에서 에러");
        }

    }

}
