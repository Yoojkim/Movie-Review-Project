package controller;

import domain.ReviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.ReviewService;


/*모든 기능들은 로그인이 되어있을 때만 수행*/
@RequestMapping("/reviews")
@Controller
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    //리뷰 작성
    @RequestMapping(value="/write",method= RequestMethod.POST)
    public ResponseEntity createReview(@RequestParam ReviewDTO reviewDTO){
        return ResponseEntity(reviewService.createReview(reviewDTO), HttpStatus.OK);
    }

    //특정 영화 리뷰 조회 (리스트로 반환)
    @RequestMapping(value="/movie/{id}",method = RequestMethod.GET)
    public ResponseEntity getMovieReviews(@PathVariable Long id){
        return ResponseEntity(reviewService.getMovieReviews(id),HttpStatus.OK);
    }

    //특정 member의 리뷰 조회
    @RequestMapping(value="/member/{id}",method =RequestMethod.GET)
    public ResponseEntity getMemberReviews(@PathVariable Long id){
        return ResponseEntity(reviewService.getMemberReviews(id),HttpStatus.OK);
    }

    //내가 쓴 리뷰 조회 (이건 따로 구현할지 여부 파악...)
    //리뷰 좋아요 기능 -> 이건 후에 ...

}
